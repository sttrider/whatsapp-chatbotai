package com.fourhands.chatbotai.vo.whatsapp;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SendMessage(@JsonProperty("messaging_product") String messagingProduct, String to, Text text,
                          Context context) {
}
