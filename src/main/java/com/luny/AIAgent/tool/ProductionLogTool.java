package com.luny.AIAgent.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

@Component
public class ProductionLogTool {

    private final String orderLogPath;
    private final String paymentLogPath;

    public ProductionLogTool(
            @Value("${production.log.path}") String orderLogPath,
            @Value("${payment.log.path}") String paymentLogPath) {

        this.orderLogPath = orderLogPath;
        this.paymentLogPath = paymentLogPath;
    }

    @Tool(description = "Fetches Order Service production log entries for a specific order ID")
    public String getOrderLogsForOrder(String orderId) {

        return readLogs(orderLogPath, orderId, "Order Service");
    }

    @Tool(description = "Fetches Payment Service production log entries for a specific order ID")
    public String getPaymentLogsForOrder(String orderId) {

        return readLogs(paymentLogPath, orderId, "Payment Service");
    }

    private String readLogs(
            String logPath,
            String orderId,
            String serviceName) {

        try {

            String logs = Files.lines(Path.of(logPath))
                    .filter(line -> line.contains(orderId))
                    .collect(Collectors.joining(System.lineSeparator()));

            if (logs.isBlank()) {
                return "No log entries found for order ID "
                        + orderId
                        + " in "
                        + serviceName;
            }

            return logs;

        } catch (Exception e) {

            return "Unable to read "
                    + serviceName
                    + " logs: "
                    + e.getMessage();
        }
    }
}