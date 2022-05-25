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
@ExternalTaskSubscription(topicName = "instance-deployment")
public class InstanceDeploymentTask implements ExternalTaskHandler {

    private final InstanceEventConsumer eventConsumer;

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        try {
            log.info("InstanceDeploymentTask received: {}", externalTask);
            Thread.sleep(5000);
            externalTaskService.complete(externalTask);
            eventConsumer.handleInstanceDeploymentEvent(externalTask.getBusinessKey());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
