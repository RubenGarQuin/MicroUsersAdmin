package com.wend.MicroLogin.domain.port.in;

import com.wend.MicroLogin.infraestructure.in.rest.dto.UserRequestDto;
import com.wend.MicroLogin.domain.model.User;

public interface CreateUserUseCase {
    User createUser(User user);
}
