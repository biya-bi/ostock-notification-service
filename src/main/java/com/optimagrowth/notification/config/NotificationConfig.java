package com.optimagrowth.notification.config;

import java.security.GeneralSecurityException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import nl.martijndwars.webpush.PushService;

@Configuration
@ComponentScan(basePackageClasses = VapidConfig.class)
class NotificationConfig {

    @Bean
    PushService getPushService(VapidConfig vapidConfig) throws GeneralSecurityException {
        return new PushService(vapidConfig.getPublicKey(), vapidConfig.getPrivateKey(), vapidConfig.getSubject());
    }

}
