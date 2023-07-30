package com.chatGPT.service;

import com.chatGPT.model.ChatGPTRequest;
import com.chatGPT.model.ChatGPTResponse;
import org.springframework.stereotype.Service;

@Service
public class ChatGPTService {
    public String processSearch(String query){
        ChatGPTRequest chatGPTRequest = new ChatGPTRequest();
        chatGPTRequest.setPrompt(query);

        ChatGPTResponse chatGPTResponse;
        return chatGPTResponse.getChoises().get(0).getText();
    }
}
