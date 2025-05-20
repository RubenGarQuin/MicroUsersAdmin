package com.wend.MicroLogin.domain.port.in;

import com.wend.MicroLogin.domain.model.User;
import com.wend.MicroLogin.infraestructure.in.rest.dto.response.UserResponseDto;

public interface ManageUserUserCase {
    User getCurrentUser(String email);
    User updateUser(Long userId,User updated);
    void updatePassword(String current, String updated, Long userId);
}
