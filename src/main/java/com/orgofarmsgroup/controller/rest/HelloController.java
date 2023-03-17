package com.orgofarmsgroup.controller.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hello")
@Slf4j
public class HelloController {
    private final MessageSource messageSource;

    @GetMapping
    public String sayHello(
            @RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false)
            Locale locale
    ) {
        log.info("hello controller: sayHello accessed");
        return messageSource.getMessage("common.hello", null, locale);
    }
}
