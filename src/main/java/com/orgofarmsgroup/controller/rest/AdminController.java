package com.orgofarmsgroup.controller.rest;

import com.orgofarmsgroup.entity.AdminEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admins")
@Slf4j
public class AdminController {
    @GetMapping(produces = {"application/json"})
    public ResponseEntity<List<?>> admins() {
        log.info("GET /admins accessed");
        return ResponseEntity.status(HttpStatus.OK).body(
                List.of(
                        new AdminEntity(999L, "admin")
                )
        );
    }
}
