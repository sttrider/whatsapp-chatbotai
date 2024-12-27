package com.fourhands.chatbotai;

import com.fourhands.chatbotai.configuration.MetaConfigurationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(MetaConfigurationProperties.class)
@Slf4j
public class ChatbotaiApplication {


    public static void main(String[] args) {
        SpringApplication.run(ChatbotaiApplication.class, args);
    }

}
