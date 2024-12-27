package com.fourhands.chatbotai.vo.whatsapp;

public record Message(
        String from,
        String id,
        String timestamp,
        String type,
        Text text
) {
}
