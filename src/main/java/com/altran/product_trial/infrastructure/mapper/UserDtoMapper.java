package com.altran.product_trial.infrastructure.mapper;

import com.altran.product_trial.domain.model.User;
import com.altran.product_trial.infrastructure.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {
    public User toDomain(UserDTO dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setFirstname(dto.getFirstname());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        return user;
    }

    public UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFirstname(user.getFirstname());
        dto.setEmail(user.getEmail());
        dto.setPassword(null);

        return dto;
    }
}
