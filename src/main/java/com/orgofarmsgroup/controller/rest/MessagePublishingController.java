package com.orgofarmsgroup.controller.rest;

import com.google.gson.Gson;
import com.orgofarmsgroup.config.RabbitMQConfig;
import com.orgofarmsgroup.dto.misc.CustomMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/messages")
@Slf4j
public record MessagePublishingController(RabbitTemplate rabbitTemplate, Gson jsonHelper) {
    @PostMapping(value = "/publish", produces = "application/json")
    public ResponseEntity<?> publish(@RequestBody CustomMessage customMessage, Principal principal) {
        log.info("publishing message {}", jsonHelper.toJson(customMessage));
        if(principal instanceof OAuth2AuthenticationToken token) {
            log.info(jsonHelper.toJson(token));
        }else if(principal instanceof UsernamePasswordAuthenticationToken token){
            log.info(jsonHelper.toJson(token));
        }
        customMessage.setUid(UUID.randomUUID().toString());
        customMessage.setCreatedDateTime(new Date());
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, customMessage);
        log.info("message published.");
        return ResponseEntity.ok(customMessage);
    }
}
