package com.luny.AIAgent.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class ProductionLogTool {

    private final String logPath;

    public ProductionLogTool(
            @Value("${production.log.path}") String logPath)
    {

        this.logPath = logPath;
    }

    @Tool(description = "Fetches recent error logs from the production application")
    public String getLogs() {

        try {
            return Files.readString(Path.of(logPath));
        } catch (Exception e) {
            return "Unable to fetch production logs: " + e.getMessage();
        }
    }
}