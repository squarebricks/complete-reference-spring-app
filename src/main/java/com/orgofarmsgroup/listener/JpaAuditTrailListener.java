package com.orgofarmsgroup.listener;

import com.orgofarmsgroup.entity.UserEntity;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class JpaAuditTrailListener {
    @PrePersist
    @PreUpdate
    @PreRemove
    private void beforeAnyUpdate(UserEntity user) {
        if (user.getUid() == 0) {
            log.info("[USER AUDIT] About to add a user");
        } else {
            log.info("[USER AUDIT] About to update/delete user: " + user.getUid());
        }
    }
    @PostPersist
    @PostUpdate
    @PostRemove
    private void afterAnyUpdate(UserEntity user) {
        log.info("[USER AUDIT] add/update/delete complete for user: " + user.getUid());
    }

    @PostLoad
    private void afterLoad(UserEntity user) {
        log.info("[USER AUDIT] user loaded from database: " + user.getUid());
    }
}
