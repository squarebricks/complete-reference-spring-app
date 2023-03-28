package com.orgofarmsgroup.exception.handler;

import com.orgofarmsgroup.dto.error.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class RestAPIRootExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> rootExceptionHandler(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }
    @ExceptionHandler(value = {BindException.class})
    public ResponseEntity<Object> handleValidationExceptions(BindException ex, WebRequest request)
    {
        log.info("ApiAdviceHandler.handleValidationExceptions()");
        Map<String, String> errors = new HashMap<>();
        ErrorDto errorDto = new ErrorDto();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        errorDto.setMessage("Bad Request. One or more fields are invalid.");
        errorDto.setTimestamp(new Timestamp(System.currentTimeMillis()));
        errorDto.setErrors(errors);
        errorDto.setStatusCode(400);
        return ResponseEntity.badRequest().body(errorDto);
    }
}
