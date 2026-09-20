package com.luny.AIAgent.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class ProductionDeploymentTool {

    @Tool(description = "Returns information about the most recent production deployment")
    public String getRecentDeployment() {

        return """
                Recent Production Deployment:

                Application: Order Service
                Version: 2.8.1
                Previous Version: 2.8.0
                Deployment Time: Today 10:30 AM
                Deployment Status: SUCCESS
                Deployed By: Release Pipeline
                """;
    }
}
