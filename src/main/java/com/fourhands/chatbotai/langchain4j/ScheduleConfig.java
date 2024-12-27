package com.fourhands.chatbotai.langchain4j;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ScheduleConfig {

    private static final String KEY = "";

    private final ScheduleTools scheduleTools;

    @Bean
    public ScheduleAssistant scheduleAssistant() {

        ChatLanguageModel gemini = GoogleAiGeminiChatModel.builder()
                .apiKey(KEY)
                .modelName("gemini-1.5-flash")
                .temperature(0.0)
                .build();

        return AiServices.builder(ScheduleAssistant.class)
                .chatLanguageModel(gemini)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(1000))
                .tools(scheduleTools)
                .build();
    }
}
