package com.orgofarmsgroup.controller.graphql;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

@SpringBootTest
@AutoConfigureGraphQlTester
@Slf4j
class UserGraphQLIntegrationTest {
    @Autowired
    private GraphQlTester graphQlTester;
    
    @Test
    @DisplayName(value = "ugc: no op test")
    void noOpTest() {
        log.info("no op test succeeded");
    }
}
