package com.orgofarmsgroup.dto.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ErrorDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -1225492362858997441L;

    private String message;
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    private Object errors;
    private String additionalInfo = "";
    private int statusCode = HttpStatus.BAD_REQUEST.value();
}
