package com.wend.MicroLogin.mapper;

import com.wend.MicroLogin.config.dto.UserRequestDto;
import com.wend.MicroLogin.domain.model.User;
import com.wend.MicroLogin.config.dto.response.UserResponseDto;

public class UserMapper {

    public static User toDomain(UserRequestDto dto) {
        return new User(null, dto.getName(), dto.getEmail(), dto.getPassword());
    }

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(user.getId(), user.getName(), user.getEmail());
    }
}
