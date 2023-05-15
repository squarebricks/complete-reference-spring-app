package com.orgofarmsgroup.controller.rest;

import com.google.gson.Gson;
import com.orgofarmsgroup.dto.response.ResponseDto;
import com.orgofarmsgroup.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final Gson jsonHelper;
    private final UserService userService;

    @GetMapping(produces = {"application/json"})
    public ResponseEntity<ResponseDto> users(Principal principal, HttpServletRequest request) {
        try{
            log.info("user controller: users() : accessed");
            if(principal instanceof OAuth2AuthenticationToken token) {
                System.out.println(jsonHelper.toJson(token));
            }else if(principal instanceof UsernamePasswordAuthenticationToken token){
                System.out.println(jsonHelper.toJson(token));
            }
            ResponseDto responseDto = new ResponseDto(request, HttpStatus.OK, userService.users());
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }catch (Exception ex) {
            log.error("user controller: users() : exception occurred : {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(new ResponseDto(request, HttpStatus.SERVICE_UNAVAILABLE, "Something went wrong."));
        }
    }
}
