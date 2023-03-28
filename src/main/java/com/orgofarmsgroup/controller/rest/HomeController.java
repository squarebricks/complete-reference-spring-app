package com.orgofarmsgroup.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Slf4j
public class HomeController{
    @GetMapping("/")
    public String index() {
        log.info("home page accessed");
        return "index";
    }
    @GetMapping("/error")
    public String error() {
        log.error("GET /error accessed");
        return "error";
    }
}
