package com.fourhands.chatbotai.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("meta")
public record MetaConfigurationProperties(String token, String graphApiToken) {
}
