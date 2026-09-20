package com.luny.AIAgent.controller;

import com.luny.AIAgent.service.AiSupportService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/aiagent")
public class SupportController {

    private final AiSupportService aiSupportService;

    public SupportController(AiSupportService aiSupportService) {
        this.aiSupportService = aiSupportService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Production Support AI Agent is running";
    }

    @GetMapping("/ask")
    public String ask(@RequestParam String question)
    {
        return aiSupportService.ask(question);
    }
}
