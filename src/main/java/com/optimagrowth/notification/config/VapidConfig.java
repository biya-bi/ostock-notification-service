package com.optimagrowth.notification.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@ConfigurationProperties(prefix = "notification.vapid")
@Getter
public class VapidConfig {

    private String publicKey;
    private String privateKey;
    private String subject;

}
