package com.luny.AIAgent.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class ProductionHealthTool {

    @Tool(description = "Checks the current health status of the production application and its dependencies")
    public String getHealth()
    {
     return """
             Application: Order Service
             Status: DOWN
             Database: UP
             Kafka: UP
             Redis: UP
             Error Rate: 18%
             """;
    }
}
