package com.wend.MicroLogin.infraestructure.in.rest.dto;


import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangePasswordRequest {

    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String currentPassword;
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String newPassword;


}
