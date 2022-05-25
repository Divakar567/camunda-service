package com.example.camunda.config;

import org.camunda.bpm.client.spring.SpringTopicSubscription;
import org.camunda.bpm.client.spring.event.SubscriptionInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class SubscriptionInitializedListener implements ApplicationListener<SubscriptionInitializedEvent> {

    @Override
    public void onApplicationEvent(SubscriptionInitializedEvent event) {
        SpringTopicSubscription topicSubscription = event.getSource();
        if (!topicSubscription.isOpen()) {
            // Start fetching for External Tasks
            topicSubscription.open();
        } else {
            // Stop fetching for External Tasks
            topicSubscription.close();
        }
    }
}
