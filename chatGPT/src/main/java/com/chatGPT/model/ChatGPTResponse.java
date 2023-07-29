package com.chatGPT.model;

import lombok.Data;

import java.util.List;

@Data
public class ChatGPTResponse {
    private List<ChatGPTChoise> choises;
}
