package com.orgofarmsgroup.controller.graphql;


import com.orgofarmsgroup.entity.UserEntity;
import com.orgofarmsgroup.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureGraphQlTester
@Slf4j
class UserGraphQLControllerTest {
    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private UserService mockedUserService;

    @Test
    @DisplayName(value = "ugc: no op test")
    void noOpTest() {
        log.info("no op test succeeded");
    }

    @Test
    @DisplayName(value = "ugc: users.get : should get list of users")
    void shouldGetListOfUsers() {
        // language=GraphQL
        String document = """
            query {
                users {
                    get {
                        uid
                        name
                        email
                    }
                }
            }
        """;

        when(mockedUserService.users()).thenReturn(
                Arrays.asList(
                        UserEntity.builder()
                                .uid(101L)
                                .name("John")
                                .email("john@email.com")
                                .build()
                )
        );

        GraphQlTester.EntityList<UserEntity> responseUsers = graphQlTester.document(document)
                .execute()
                .path("users.get")
                .entityList(UserEntity.class);

        UserEntity user = responseUsers.get().get(0);
        assertEquals(101L, user.getUid());
        assertEquals("John", user.getName());
        assertEquals("john@email.com", user.getEmail());
    }
}
