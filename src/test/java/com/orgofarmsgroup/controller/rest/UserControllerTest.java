package com.orgofarmsgroup.controller.rest;

import com.orgofarmsgroup.entity.UserEntity;
import com.orgofarmsgroup.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService mockedUserService;

    @WithMockUser(username = "user")
    @Test
    @DisplayName(value = "uc: users : should return list of users")
    void shouldGetListOfUsers() throws Exception {
        List<UserEntity> staticUsers = new ArrayList<>();
        staticUsers.add(new UserEntity(101L, "John", "john@email.com"));
        when(mockedUserService.users()).thenReturn(staticUsers);

        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value("200"))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].uid").value("101"))
                .andExpect(jsonPath("$.data[0].name").value("John"))
                .andExpect(jsonPath("$.data[0].email").value("john@email.com"))
                .andExpect(jsonPath("$.requestObject.requestMethod").value("GET"));
    }

    @WithMockUser(username = "user")
    @Test
    @DisplayName(value = "uc: users: should throw service unavailable")
    void shouldThrowServiceUnavailable() throws Exception {
        when(mockedUserService.users()).thenThrow(new RuntimeException("intentionally thrown"));

        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isServiceUnavailable());
    }
}
