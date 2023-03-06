package com.orgofarmsgroup.service;

import com.orgofarmsgroup.entity.UserEntity;
import com.orgofarmsgroup.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@Slf4j
class UserServiceTest {
    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    @Test
    @DisplayName(value = "user service: users: should return list of users")
    void testUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(
                UserEntity.builder().uid(101L).name("John").email("john@email.com").build()
        ));

        List<UserEntity> users = userService.users();

        verify(userRepository).findAll();

        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals(101L, users.get(0).getUid());
        assertEquals("John", users.get(0).getName());
        assertEquals("john@email.com", users.get(0).getEmail());
    }
}
