package com.luny.AIAgent.service;

import com.luny.AIAgent.tool.ProductionDeploymentTool;
import com.luny.AIAgent.tool.ProductionHealthTool;
import com.luny.AIAgent.tool.ProductionLogTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiSupportService {

    private final ChatClient chatClient;

    private final ProductionHealthTool productionHealthTool;
    private final ProductionLogTool productionLogTool;
    private final ProductionDeploymentTool productionDeploymentTool;

    public AiSupportService(
            ChatClient.Builder chatClientBuilder,
            ProductionHealthTool productionHealthTool,
            ProductionLogTool productionLogTool,
            ProductionDeploymentTool productionDeploymentTool) {

        this.chatClient = chatClientBuilder.build();

        this.productionHealthTool = productionHealthTool;
        this.productionLogTool = productionLogTool;
        this.productionDeploymentTool = productionDeploymentTool;
    }

    public String ask(String question) {

        return chatClient
                .prompt()
                .user(question)
                .tools(
                        productionHealthTool,
                        productionLogTool,
                        productionDeploymentTool
                )
                .call()
                .content();
    }
}