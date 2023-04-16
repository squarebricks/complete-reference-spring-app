package com.orgofarmsgroup.controller.rest;

import com.orgofarmsgroup.dto.request.DummyDTO;
import com.orgofarmsgroup.dto.response.ResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(produces = {"application/json"})
    public ResponseEntity<ResponseDto> sayHello(@Valid @RequestBody DummyDTO dummyDTO, HttpServletRequest request) {
        ResponseDto responseDto = new ResponseDto(request, HttpStatus.OK, dummyDTO);
        return ResponseEntity.ok().body(responseDto);
    }
}
