package com.wend.MicroLogin.domain.port.in;

import com.wend.MicroLogin.config.dto.UserRequestDto;
import com.wend.MicroLogin.domain.model.User;

public interface CreateUserUseCase {
    User createUser(UserRequestDto user);
}
