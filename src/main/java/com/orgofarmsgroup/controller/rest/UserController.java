package com.orgofarmsgroup.controller.rest;

import com.orgofarmsgroup.dto.response.ResponseDto;
import com.orgofarmsgroup.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping(produces = {"application/json"})
    public ResponseEntity<ResponseDto> users(HttpServletRequest request) {
        try{
            log.info("user controller: users() : accessed");
            ResponseDto responseDto = new ResponseDto(request, HttpStatus.OK, userService.users());
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }catch (Exception ex) {
            log.error("user controller: users() : exception occurred : {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(new ResponseDto(request, HttpStatus.SERVICE_UNAVAILABLE, "Something went wrong."));
        }
    }
}
