package com.wend.MicroLogin.infraestructure.in.rest.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequestDto {
    private String name="";
    private String email="";
}
