package com.example.camunda.client;

import org.camunda.bpm.engine.rest.dto.message.CorrelationMessageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "MessageCorrelationClient", url = "${camunda.bpm.client.base-url}")
public interface MessageCorrelationClient {

    @PostMapping("/message")
    void correlateMessage(@RequestBody CorrelationMessageDto correlationMessageDto);
}
