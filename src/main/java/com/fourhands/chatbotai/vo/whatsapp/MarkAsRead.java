package com.fourhands.chatbotai.vo.whatsapp;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MarkAsRead(@JsonProperty("messaging_product") String messagingProduct, String status,
                         @JsonProperty("message_id") String messageId) {
}
