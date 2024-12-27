package com.fourhands.chatbotai.vo.whatsapp;

import java.util.List;

public record Entry(
        String id,
        List<Change> changes) {
}
