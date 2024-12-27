package com.fourhands.chatbotai.vo.whatsapp;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Contact(
        Profile profile,
        @JsonProperty("wa_id") String waId) {
}
