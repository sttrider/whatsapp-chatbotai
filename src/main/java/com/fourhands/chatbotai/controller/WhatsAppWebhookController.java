package com.fourhands.chatbotai.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fourhands.chatbotai.configuration.MetaConfigurationProperties;
import com.fourhands.chatbotai.langchain4j.ScheduleAssistant;
import com.fourhands.chatbotai.vo.whatsapp.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/whatsapp")
@Slf4j
@RequiredArgsConstructor
public class WhatsAppWebhookController {

    private static final String MODE = "subscribe";

    private final ObjectMapper objectMapper;
    private final ScheduleAssistant scheduleAssistant;
    private final MetaConfigurationProperties metaConfigurationProperties;

    @PostMapping("/webhook")
    public void webhook(@RequestBody MessageRequest msg) throws JsonProcessingException {
        log.info(msg.toString());

        Value value = msg.entry().getFirst().changes().getFirst().value();
        if (value.messages().isEmpty()) {
            return;
        }
        Message message = value.messages().get().getFirst();
        String phoneNumberId = value.metadata().phoneNumberId();

        String chatResponse = scheduleAssistant.chat(message.from(), message.text().body(), message.from());

        String requestBodyMarkAsRead = objectMapper
                .writeValueAsString(new MarkAsRead("whatsapp", "read", message.id()));

        String requestBodyMSendMessage = objectMapper
                .writeValueAsString(new SendMessage("whatsapp", message.from(), new Text(chatResponse), new Context(message.id())));

        HttpRequest requestMarkAsRead = HttpRequest.newBuilder()
                .uri(URI.create("https://graph.facebook.com/v21.0/" + phoneNumberId + "/messages"))
                .header("Authorization", "Bearer " + metaConfigurationProperties.graphApiToken())
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBodyMarkAsRead))
                .build();

        HttpRequest requestSendMessage = HttpRequest.newBuilder()
                .uri(URI.create("https://graph.facebook.com/v21.0/" + phoneNumberId + "/messages"))
                .header("Authorization", "Bearer " + metaConfigurationProperties.graphApiToken())
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBodyMSendMessage))
                .build();

        try (HttpClient httpClient = HttpClient.newHttpClient()
        ) {
            HttpResponse<String> responseMarkAsRead = httpClient.send(requestMarkAsRead, HttpResponse.BodyHandlers.ofString());
            log.info("Response mark as read: [{}] {}", responseMarkAsRead.statusCode(), responseMarkAsRead.body());

            HttpResponse<String> responseSendMessage = httpClient.send(requestSendMessage, HttpResponse.BodyHandlers.ofString());
            log.info("Response send message: [{}] {}", responseSendMessage.statusCode(), responseSendMessage.body());

        } catch (IOException | InterruptedException e) {
            log.error("Error sending to meta.", e);
            throw new RuntimeException(e);
        }


    }

    @GetMapping("/webhook")
    public ResponseEntity<String> webhookValidation(@RequestParam("hub.mode") String mode, @RequestParam("hub.challenge") String challenge, @RequestParam("hub.verify_token") String verifyToken) {
        if (MODE.equals(mode) && metaConfigurationProperties.token().equals(verifyToken)) {
            return ResponseEntity.ok().body(challenge);
        }
        return ResponseEntity.status(403).build();
    }
}
