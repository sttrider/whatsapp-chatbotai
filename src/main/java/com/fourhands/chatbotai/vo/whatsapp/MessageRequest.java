package com.fourhands.chatbotai.vo.whatsapp;

import java.util.List;

public record MessageRequest(
        String object,
        List<Entry> entry) {
}
