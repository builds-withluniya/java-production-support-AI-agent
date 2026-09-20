package com.luny.AIAgent.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class ProductionLogTool {

    @Tool(description = "Fetches recent error logs from the production application")
    public String getLogs() {

        return """
                Recent Production Logs:

                10:42:11 ERROR OrderService - Failed to process order 78452
                10:42:12 ERROR OrderService - Database connection timeout
                10:42:15 ERROR OrderService - Retry attempt 1 failed
                10:42:18 ERROR OrderService - Retry attempt 2 failed
                10:42:20 ERROR KafkaConsumer - Consumer lag increased to 8500
                """;
    }
}