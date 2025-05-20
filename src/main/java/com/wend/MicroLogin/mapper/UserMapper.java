package com.wend.MicroLogin.mapper;

import com.wend.MicroLogin.infraestructure.in.rest.dto.UpdateUserRequestDto;
import com.wend.MicroLogin.infraestructure.in.rest.dto.UserRequestDto;
import com.wend.MicroLogin.domain.model.User;
import com.wend.MicroLogin.infraestructure.in.rest.dto.response.UserResponseDto;

public class UserMapper {

    public static User toDomain(UserRequestDto dto) {
        return new User(null, dto.getName(), dto.getEmail(), dto.getPassword());
    }

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(user.getName(), user.getEmail());
    }

    public static User toDomain(UpdateUserRequestDto dto){
        return new User(null, dto.getName(), dto.getEmail(), null);
    }

}
