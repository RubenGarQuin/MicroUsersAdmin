package com.wend.MicroLogin.config;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("app", Map.of(
                "name", "User MicroService",
                "version", "0.0.1",
                "description", "Microservicio para gestión de usuarios"
        ));
    }
}
