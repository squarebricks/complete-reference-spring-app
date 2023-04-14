package com.orgofarmsgroup.controller.rest;

import com.google.gson.Gson;
import com.orgofarmsgroup.dto.request.DummyDTO;
import com.orgofarmsgroup.entity.UserEntity;
import com.orgofarmsgroup.exception.handler.RestAPIRootExceptionHandler;
import com.orgofarmsgroup.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
class HelloControllerTest {
    private MockMvc mockMvc;
    @MockBean
    private UserService mockedUserService;

    @Autowired
    private HelloController helloController;
    @Autowired
    private Gson jsonHelper;
    @Autowired
    private RestAPIRootExceptionHandler restAPIRootExceptionHandler;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(helloController)
                .setControllerAdvice(restAPIRootExceptionHandler)
                .build();
    }

    @Test
    @DisplayName(value = "uc: users : should return list of users")
    void shouldGetListOfUsers() throws Exception {
        DummyDTO dummyDTO = new DummyDTO();
        dummyDTO.setName("John");
        dummyDTO.setEmail("john@email.com");

        mockMvc.perform(post("/hello")
                        .contentType("application/json")
                        .content(jsonHelper.toJson(dummyDTO))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.requestObject.requestMethod").value("POST"));
    }

    @Test
    @DisplayName(value = "uc: users : sayHelloWithDummyDTOShouldThrowValidationException")
    void sayHelloWithDummyDTOShouldThrowValidationException() throws Exception {
        DummyDTO dummyDTO = new DummyDTO();
        dummyDTO.setName("B89");
        dummyDTO.setEmail("john@email.com");

        mockMvc.perform(post("/hello")
                        .contentType("application/json")
                        .accept("application/json")
                        .content(jsonHelper.toJson(dummyDTO))
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}