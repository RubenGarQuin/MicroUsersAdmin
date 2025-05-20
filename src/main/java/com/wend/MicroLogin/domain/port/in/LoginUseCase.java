package com.wend.MicroLogin.domain.port.in;

import com.wend.MicroLogin.infraestructure.in.rest.dto.LoginRequestDto;
import com.wend.MicroLogin.infraestructure.in.rest.dto.response.LoginResponseDto;

public interface LoginUseCase {
    LoginResponseDto login(String email, String password);
}
