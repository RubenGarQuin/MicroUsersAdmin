package com.wend.MicroLogin.domain.port.in;

import com.wend.MicroLogin.infraestructure.in.rest.dto.response.UserResponseDto;

public interface GetCurrentUser {
    UserResponseDto getCurrentUser(String email);
}
