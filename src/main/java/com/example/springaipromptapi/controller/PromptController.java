package com.example.springaipromptapi.controller;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PromptController {

    @Autowired
    private ChatClient     chatClient;

    @GetMapping("/prompt/{adjective}/{topic}")
    public String GetPrompt(@PathVariable String adjective, @PathVariable String topic ) {
        PromptTemplate promptTemplate = new PromptTemplate("Tell me a {adjective} joke about {topic}");

        Prompt prompt = promptTemplate.create(Map.of("adjective", adjective, "topic", topic));

        return chatClient.call(prompt).getResult().getOutput().getContent();
    }

}
