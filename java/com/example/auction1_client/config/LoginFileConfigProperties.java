package com.example.auction1_client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "file", ignoreUnknownFields = false)
public class LoginFileConfigProperties {

    private String users;

}
