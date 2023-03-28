package com.orgofarmsgroup.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MultipartVariableMapperTest {
    @Test
    @DisplayName(value = "multipart variable mapper: map variable")
    void testMapVariable() {
        MultipartVariableMapper.mapVariable(
                "variables.file",
                new HashMap<>(),
                null
        );
    }

    @Test
    @DisplayName(value = "multipart variable mapper: map variable passing variables map")
    void testMapVariablePassingVariable() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("file", new Object());
        assertThrows(RuntimeException.class, () -> {
            MultipartVariableMapper.mapVariable("variables.file", map, null);
        });
    }

    @Test
    @DisplayName(value = "multipart variable mapper: map variable passing 2 nulls in variables map")
    void testMapVariablePassing2Variables() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("file", null);
        map.put("other", null);
        assertThrows(RuntimeException.class, () -> {
            MultipartVariableMapper.mapVariable("variables.file.other", map, null);
        });
    }

    @Test
    @DisplayName(value = "multipart variable mapper: map variable determine mapper throws RE")
    void testMapVariableDetermineMapperThrowsRuntimeException() {
        assertThrows(RuntimeException.class, () -> {
            MultipartVariableMapper.mapVariable(
                    "variables.file",
                    null,
                    null
            );
        });
    }



    @Test
    @DisplayName(value = "multipart variable mapper: map variable should throw RuntimeException")
    void testMapVariableShouldThrowRuntimeException() {
        assertThrows(RuntimeException.class, () -> {
            HashMap<String, Object> map = new HashMap<>();
            MultipartVariableMapper.mapVariable(
                    "",
                    map,
                    null
            );
        });
        assertThrows(RuntimeException.class, () -> {
            HashMap<String, Object> map = new HashMap<>();
            MultipartVariableMapper.mapVariable(
                    "other_variables.file",
                    map,
                    null
            );
        });
    }

}