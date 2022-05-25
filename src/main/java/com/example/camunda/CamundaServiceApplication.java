package com.example.camunda;

import org.camunda.bpm.client.spring.SpringTopicSubscription;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableFeignClients
@SpringBootApplication
public class CamundaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamundaServiceApplication.class, args);
	}

}
