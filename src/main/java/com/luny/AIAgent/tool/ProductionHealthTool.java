package com.luny.AIAgent.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ProductionHealthTool {

    private final RestClient restClient;

    public ProductionHealthTool(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.build();
    }

    @Tool(description = "Checks the current health status of the production application and its dependencies")
    public String getHealth()
    {
     return restClient.get().
             uri("http://localhost:8081/actuator/health")
             .retrieve()
             .body(String.class);
    }
}
