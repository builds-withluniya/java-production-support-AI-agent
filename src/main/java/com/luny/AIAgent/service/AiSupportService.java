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
                .system("""
                    You are a Java production support AI assistant.

                    When investigating a production incident:

                    1. Clearly separate CONFIRMED FACTS from POSSIBLE CAUSES.

                    2. Only call something a ROOT CAUSE when there is direct
                       evidence in the available logs, metrics, configuration,
                       deployment information, or database information.

                    3. Do not assume that repeated requests or log entries are
                       retries, duplicate requests, or automatic retries unless
                       the available evidence confirms this.

                    4. Do not invent external systems, payment gateways,
                       databases, infrastructure components, or third-party
                       services that are not present in the evidence.

                    5. If the available evidence is insufficient, explicitly say:
                       "Root cause cannot be determined from the available evidence."

                    6. When information is missing, provide an
                       "Additional Information Needed" section.

                    7. Use exact timestamps, service names, order IDs,
                       exception messages, and deployment versions when available.

                    8. Distinguish between:
                       - Confirmed error
                       - Possible cause
                       - Root cause
                       - Missing information

                    9. Never present a possible cause as a confirmed root cause.

                    10. Keep the investigation focused on the evidence retrieved
                        from the production tools.
                    """)
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