package com.orgofarmsgroup.util;

import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UploadCoercingTest {
    private UploadCoercing uploadCoercing;
    @BeforeEach
    void setup() {
        uploadCoercing = new UploadCoercing();
    }

    @Test
    @DisplayName(value = "upload coercing: serializable() throws  CoercingSerializeException")
    void testSerializableThrows() {
        assertThrows(CoercingSerializeException.class, () -> {
            uploadCoercing.serialize(new Object());
        });
    }

    @Test
    @DisplayName(value = "upload coercing: parseValue() throws CoercingParseValueException")
    void testParseValueReturnsMultipartFile() {
        MultipartFile multipartFile = new MockMultipartFile("text", "some data in the file".getBytes());
        MultipartFile response = uploadCoercing.parseValue(multipartFile);

        assertEquals("text", response.getName());
    }

    @Test
    @DisplayName(value = "upload coercing: parseValue() throws CoercingParseValueException")
    void testParseValueThrowsCoercingParseValueException() {
        assertThrows(CoercingParseValueException.class, () -> {
            uploadCoercing.parseValue(new Object());
        });
    }

    @Test
    @DisplayName(value = "upload coercing: parseLiteral() throws CoercingParseLiteralException")
    void testParseLiteral() {
        assertThrows(CoercingParseLiteralException.class, () -> {
            uploadCoercing.parseLiteral(new Object());
        });
    }

}