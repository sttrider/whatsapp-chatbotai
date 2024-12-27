package com.fourhands.chatbotai.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("gemini")
public record GeminiConfigurationProperties(String apiKey) {
}
