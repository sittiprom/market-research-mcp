package com.market_research_mcp.client.controller;

import com.market_research_mcp.client.model.request.ChatRequest;
import com.market_research_mcp.client.model.response.ChatResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ChatController {
    private final ChatClient chatClient;

    public ChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @PostMapping("/api/ask")
    public String ask(@RequestBody ChatRequest chatRequest) {
        return chatClient.prompt().user(chatRequest.getQuestion())
                .call().content();

    }
}
