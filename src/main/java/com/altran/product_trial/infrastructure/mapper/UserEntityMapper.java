package com.altran.product_trial.infrastructure.mapper;

import com.altran.product_trial.domain.model.User;
import com.altran.product_trial.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {
    public UserEntity toEntity(User domainUser) {
        if (domainUser == null) {
            return null;
        }

        UserEntity entity = new UserEntity();
        entity.setId(domainUser.getId());
        entity.setUsername(domainUser.getUsername());
        entity.setFirstname(domainUser.getFirstname());
        entity.setEmail(domainUser.getEmail());
        entity.setPassword(domainUser.getPassword());

        return entity;
    }

    public User toDomain(UserEntity entityUser) {
        if (entityUser == null) {
            return null;
        }

        User domain = new User();
        domain.setId(entityUser.getId());
        domain.setUsername(entityUser.getUsername());
        domain.setFirstname(entityUser.getFirstname());
        domain.setEmail(entityUser.getEmail());
        domain.setPassword(entityUser.getPassword());

        return domain;
    }
}
