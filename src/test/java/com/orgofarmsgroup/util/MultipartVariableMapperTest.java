package com.orgofarmsgroup.util;

import com.orgofarmsgroup.exception.AppException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

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
        Executable mapVariable = () -> MultipartVariableMapper.mapVariable("variables.file", map, null);
        assertThrows(AppException.class, mapVariable);
    }

    @Test
    @DisplayName(value = "multipart variable mapper: map variable passing 2 nulls in variables map")
    void testMapVariablePassing2Variables() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("file", null);
        map.put("other", null);
        Executable mapVariable = () -> MultipartVariableMapper.mapVariable("variables.file.other", map, null);
        assertThrows(AppException.class, mapVariable);
    }

    @Test
    @DisplayName(value = "multipart variable mapper: map variable determine mapper throws RE")
    void testMapVariableDetermineMapperThrowsAppException() {
        HashMap<String, Object> map = new HashMap<>();
        Executable mapVariable = () -> MultipartVariableMapper.mapVariable("variables.file",null,null);
        assertThrows(AppException.class, mapVariable);
    }

    @Test
    @DisplayName(value = "multipart variable mapper: map variable should throw AppException")
    void testMapVariableShouldThrowAppException() {
        HashMap<String, Object> map = new HashMap<>();
        Executable mapVariable = () -> MultipartVariableMapper.mapVariable("", map, null);
        assertThrows(AppException.class, mapVariable);
    }

    @Test
    @DisplayName(value = "multipart variable mapper: invalid path: map variable should throw AppException")
    void testMapVariableInvalidPathShouldThrowAppException() {
        HashMap<String, Object> map = new HashMap<>();
        Executable mapVariable = () -> MultipartVariableMapper.mapVariable("other_variables.file",map,null);
        assertThrows(AppException.class, mapVariable);
    }
}