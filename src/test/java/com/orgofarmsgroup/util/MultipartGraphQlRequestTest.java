package com.orgofarmsgroup.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MultipartGraphQlRequestTest {
    private MultipartGraphQlRequest multipartGraphQlRequest;

    @BeforeEach
    void setup() throws URISyntaxException {
        String query = """
            query {
                get
            }
        """;
        String operationName = "query";
        HashMap<String, Object> variables = new HashMap<>();
        HashMap<String, Object> extensions = new HashMap<>();
        URI uri = new URI("localhost:8080");
        HttpHeaders headers = new HttpHeaders();
        String id = "id";
        Locale locale = Locale.US;
        multipartGraphQlRequest = new MultipartGraphQlRequest(query, operationName, variables, extensions, uri, headers, id, locale);
    }

    @Test
    @DisplayName(value = "multipart gql request: get document")
    void testGetDocument() throws URISyntaxException {
        assertNotNull(multipartGraphQlRequest.getDocument());
    }

    @Test
    @DisplayName(value = "multipart gql request: get operation name")
    void testGetOperationName() throws URISyntaxException {
        assertNotNull(multipartGraphQlRequest.getOperationName());
    }

    @Test
    @DisplayName(value = "multipart gql request: get variables")
    void testGetVariables() throws URISyntaxException {
        assertNotNull(multipartGraphQlRequest.getVariables());
    }

    @Test
    @DisplayName(value = "multipart gql request: get extensions")
    void testGetExtensions() throws URISyntaxException {
        assertNotNull(multipartGraphQlRequest.getExtensions());
    }

}