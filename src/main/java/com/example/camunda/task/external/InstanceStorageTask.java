package com.example.camunda.task.external;

import com.example.camunda.consumer.InstanceEventConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
@ExternalTaskSubscription(topicName = "instance-storage")
public class InstanceStorageTask implements ExternalTaskHandler {

    private final InstanceEventConsumer eventConsumer;

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        try {
            log.info("InstanceStorageTask received: {}", externalTask);
            Thread.sleep(10000);
            externalTaskService.complete(externalTask);
            eventConsumer.handleInstanceStorageEvent(externalTask.getBusinessKey());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
