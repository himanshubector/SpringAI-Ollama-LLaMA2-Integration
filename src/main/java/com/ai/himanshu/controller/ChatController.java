package com.ai.himanshu.controller;

import com.ai.himanshu.service.Llama2AiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ChatController {
    private final OllamaChatModel chatModel;
    private final Llama2AiService llama2AiService;

    public ChatController(OllamaChatModel chatModel, Llama2AiService llama2AiService) {
        this.chatModel = chatModel;
        this.llama2AiService = llama2AiService;
    }

    @GetMapping("/ai/generate")
    public String generateResponse(@RequestParam(value = "prompt", defaultValue = "what is java") String prompt) {
        log.info("Sending prompt:: {} to Llama2 model ", prompt);
        return llama2AiService.generateResponse(prompt);
    }


    @GetMapping("/ai/generateStream")
    public Flux<String> generateStreamResponse(@RequestParam(value = "message", defaultValue = "what is python") String message) {
        log.info("Sending prompt message:: {} to Llama2 model ", message);
        return llama2AiService.generateStreamResponse(message);
    }
}
