package com.orgofarmsgroup.repository;

import com.orgofarmsgroup.entity.UserEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository repositoryUnderTest;

    @AfterEach
    void tearDown() {
        repositoryUnderTest.deleteAll();
    }

    @Test
    void testFindAll() {
        List<UserEntity> userEntities = repositoryUnderTest.findAll();
        assertNotNull(userEntities);
    }
}
