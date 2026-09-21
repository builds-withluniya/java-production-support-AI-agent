package com.luny.AIAgent.controller;

import com.luny.AIAgent.service.AiSupportService;
import com.luny.AIAgent.tool.ProductionHealthTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/aiagent")
public class SupportController {

    private final AiSupportService aiSupportService;

    private final ProductionHealthTool productionHealthTool;

    public SupportController(AiSupportService aiSupportService,ProductionHealthTool productionHealthTool) {
        this.aiSupportService = aiSupportService;
        this.productionHealthTool = productionHealthTool;
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
    @GetMapping("/health-tool")
    public String healthTool() {
        return productionHealthTool.getHealth();
    }
}
