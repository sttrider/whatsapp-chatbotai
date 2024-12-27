package com.fourhands.chatbotai.vo.whatsapp;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Metadata(
        @JsonProperty("display_phone_number")
        String displayPhoneNumber,
        @JsonProperty("phone_number_id")
        String phoneNumberId
) {
}
