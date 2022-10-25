package com.noirix.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("jwt")
//@Profile("dev")
@Data
public class JwtSecurityConfig {

    private String secret;

    private Integer expiration;
}
