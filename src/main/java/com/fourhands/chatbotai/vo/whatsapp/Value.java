package com.fourhands.chatbotai.vo.whatsapp;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Optional;

public record Value(
        @JsonProperty("messaging_product") String messagingProduct,
        Metadata metadata,
        List<Contact> contacts,
        Optional<List<Message>> messages
) {
}
