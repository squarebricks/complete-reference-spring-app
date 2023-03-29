package com.orgofarmsgroup.util;

import com.orgofarmsgroup.exception.AppException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MultipartVariableMapperTest {
    @Test
    @DisplayName(value = "multipart variable mapper: map variable")
    void testMapVariable() {
        MultipartVariableMapper.mapVariable(
                "variables.file",
                new HashMap<>(),
                null
        );
        assertTrue(true);
    }

    @Test
    @DisplayName(value = "multipart variable mapper: map variable passing variables map")
    void testMapVariablePassingVariable() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("file", new Object());
        assertThrows(AppException.class, () -> {
            MultipartVariableMapper.mapVariable("variables.file", map, null);
        });
    }

    @Test
    @DisplayName(value = "multipart variable mapper: map variable passing 2 nulls in variables map")
    void testMapVariablePassing2Variables() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("file", null);
        map.put("other", null);
        assertThrows(AppException.class, () -> {
            MultipartVariableMapper.mapVariable("variables.file.other", map, null);
        });
    }

    @Test
    @DisplayName(value = "multipart variable mapper: map variable determine mapper throws RE")
    void testMapVariableDetermineMapperThrowsAppException() {
        assertThrows(AppException.class, () -> {
            MultipartVariableMapper.mapVariable(
                    "variables.file",
                    null,
                    null
            );
        });
    }

    @Test
    @DisplayName(value = "multipart variable mapper: map variable should throw AppException")
    void testMapVariableShouldThrowAppException() {
        assertThrows(AppException.class, () -> {
            HashMap<String, Object> map = new HashMap<>();
            MultipartVariableMapper.mapVariable(
                    "",
                    map,
                    null
            );
        });
    }

    @Test
    @DisplayName(value = "multipart variable mapper: invalid path: map variable should throw AppException")
    void testMapVariableInvalidPathShouldThrowAppException() {
        assertThrows(AppException.class, () -> {
            HashMap<String, Object> map = new HashMap<>();
            MultipartVariableMapper.mapVariable(
                    "other_variables.file",
                    map,
                    null
            );
        });
    }
}
