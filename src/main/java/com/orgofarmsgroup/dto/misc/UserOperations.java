package com.orgofarmsgroup.dto.misc;

import com.orgofarmsgroup.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOperations {
    private List<UserEntity> get;
    private UserEntity getById;
    private UserEntity post;
}
