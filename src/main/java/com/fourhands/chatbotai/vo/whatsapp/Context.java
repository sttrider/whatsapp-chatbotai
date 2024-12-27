package com.fourhands.chatbotai.vo.whatsapp;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Context(@JsonProperty("message_id") String messageId) {
}
