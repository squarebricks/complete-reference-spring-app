package com.orgofarmsgroup.controller.graphql;


import com.orgofarmsgroup.dto.misc.UserOperations;
import com.orgofarmsgroup.entity.UserEntity;
import com.orgofarmsgroup.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserGraphQLController {
    private final UserService userService;

    @QueryMapping(name = "users")
    public UserOperations usersQuery() {
        log.info("user graphql controller : users query : accessed");
        return new UserOperations();
    }

    @MutationMapping(name = "users")
    public UserOperations usersMutation() {
        log.info("user graphql controller: users mutation : accessed");
        return new UserOperations();
    }

    @SchemaMapping(typeName = "UserOperations", field = "get")
    public List<UserEntity> get() {
        log.info("user graphql controller: users.get query : accessed");
        return userService.users();
    }
}
