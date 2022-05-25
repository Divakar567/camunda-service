package com.example.camunda.consumer;

import com.example.camunda.client.MessageCorrelationClient;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.rest.dto.message.CorrelationMessageDto;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class InstanceEventConsumer {

    private static final String INSTANCE_DEPLOYMENT_MESSAGE_NAME = "INSTANCE_DEPLOYMENT_MESSAGE";

    private static final String INSTANCE_STORAGE_MESSAGE_NAME = "INSTANCE_STORAGE_MESSAGE";

    private final MessageCorrelationClient messageCorrelationClient;

    @Async
    // @KafkaListener(topic = "instance-deployment-topic")
    @Retry(name = "camunda-service")
    public void handleInstanceDeploymentEvent(String businessKey) throws InterruptedException {
        log.info("Event received for {}: {}", INSTANCE_DEPLOYMENT_MESSAGE_NAME, businessKey);
        Thread.sleep(5000);
        CorrelationMessageDto correlationMessageDto = new CorrelationMessageDto();
        correlationMessageDto.setMessageName(INSTANCE_DEPLOYMENT_MESSAGE_NAME);
        correlationMessageDto.setBusinessKey(businessKey);
        messageCorrelationClient.correlateMessage(correlationMessageDto);
        log.info("Correlate message published");
    }

    @Async
    // @KafkaListener(topic = "gwp-topic")
    @Retry(name = "camunda-service")
    public void handleInstanceStorageEvent(String businessKey) throws InterruptedException {
        log.info("Event received for {}: {}", INSTANCE_STORAGE_MESSAGE_NAME, businessKey);
        Thread.sleep(10000);
        CorrelationMessageDto correlationMessageDto = new CorrelationMessageDto();
        correlationMessageDto.setMessageName(INSTANCE_STORAGE_MESSAGE_NAME);
        correlationMessageDto.setBusinessKey(businessKey);
        messageCorrelationClient.correlateMessage(correlationMessageDto);
        log.info("Correlate message published");
    }
}
