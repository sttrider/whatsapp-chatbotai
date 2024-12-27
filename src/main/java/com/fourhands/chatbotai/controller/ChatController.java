package com.fourhands.chatbotai.controller;

import com.fourhands.chatbotai.langchain4j.ScheduleAssistant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
@Slf4j
public class ChatController {


    private final ScheduleAssistant scheduleAssistant;


    @GetMapping("/hello")
    public String hello(@RequestParam String message) {


        return scheduleAssistant.chat("1", message, null);
    }
}
