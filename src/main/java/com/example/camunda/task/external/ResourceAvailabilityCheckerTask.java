package com.example.camunda.task.external;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ExternalTaskSubscription(topicName = "resource-availability-checker")
public class ResourceAvailabilityCheckerTask implements ExternalTaskHandler {

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        try {
            log.info("ResourceAvailabilityCheckerTask received: {}", externalTask);
            Thread.sleep(10000);
            externalTaskService.complete(externalTask);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
