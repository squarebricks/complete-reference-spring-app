package com.orgofarmsgroup.controller.rest;

import com.google.gson.Gson;
import com.orgofarmsgroup.config.RabbitMQConfig;
import com.orgofarmsgroup.dto.misc.CustomMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/messages")
@Slf4j
public record MessagePublishingController(RabbitTemplate rabbitTemplate, Gson jsonHelper) {
    @PostMapping(value = "/publish", produces = "application/json")
    public ResponseEntity<?> publish(@RequestBody CustomMessage customMessage) {
        log.info("publishing message {}", jsonHelper.toJson(customMessage));
        customMessage.setUid(UUID.randomUUID().toString());
        customMessage.setCreatedDateTime(new Date());
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, customMessage);
        log.info("message published.");
        return ResponseEntity.ok(customMessage);
    }
}
