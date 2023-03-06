package com.orgofarmsgroup.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Transient;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -1225492362858997441L;

    @JsonIgnore
    @Transient
    private HttpServletRequest request;

    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    private Object data;
    private String message;
    private int statusCode = HttpStatus.OK.value();
    private String additionalInfo;
    private Object requestObject;

    public ResponseDto(HttpServletRequest request, HttpStatus statusCode, Object data) {
        this.data = data;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.mapFields(request, statusCode);
    }
    private void mapFields(HttpServletRequest request, HttpStatus statusCode) {
        this.request = request;
        this.statusCode = statusCode.value();
        this.requestObject = this.buildRequestObject();
    }
    private Object buildRequestObject(){
        Map<String, Object> requestObject = new HashMap<>();
        requestObject.put("requestURL", this.getFullRequestURL());
        requestObject.put("requestMethod", this.request.getMethod());
        return requestObject;
    }

    private String getFullRequestURL(){
        return UriComponentsBuilder.fromHttpRequest(new ServletServerHttpRequest(this.request))
                                    .build()
                                    .toUriString();
    }
}
