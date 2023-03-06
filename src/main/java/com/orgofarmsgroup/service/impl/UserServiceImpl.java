package com.orgofarmsgroup.service.impl;

import com.orgofarmsgroup.entity.UserEntity;
import com.orgofarmsgroup.repository.UserRepository;
import com.orgofarmsgroup.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public List<UserEntity> users() {
        log.info("user service : users() accessed");
        return userRepository.findAll();
    }
}
