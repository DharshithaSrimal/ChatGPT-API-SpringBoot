package com.chatGPT.service;

import com.chatGPT.model.ChatGPTRequest;
import com.chatGPT.model.ChatGPTResponse;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChatGPTService {
    @Value("${OPEN_AI_URL}")
    private String OPEN_AI_URL;
    @Value("${OPEN_AI_KEY}")
    private String OPEN_AI_KEY;
    public String processSearch(String query){
        ChatGPTRequest chatGPTRequest = new ChatGPTRequest();
        chatGPTRequest.setPrompt(query);

        String url = OPEN_AI_URL;
        HttpPost post = new HttpPost(url);
        post.addHeader("Content-Type","application/json");
        post.addHeader("Authorization","Bearer" + OPEN_AI_KEY);
        Gson gson = new Gson();
        String body = gson.toJson(chatGPTRequest);

        try {
            final StringEntity entity = new StringEntity(body);

            try (CloseableHttpClient httpClient = HttpClients.custom().build();
                 CloseableHttpResponse response = httpClient.execute(post)) {

                String responseBody = EntityUtils.toString(response.getEntity());
                ChatGPTResponse chatGPTResponse = gson.fromJson(responseBody, ChatGPTResponse.class);
                return chatGPTResponse.getChoises().get(0).getText();
            } catch (Exception e) {
                return "failed";
            }
        } catch(Exception e){
            return "failed";
        }
    }
}
