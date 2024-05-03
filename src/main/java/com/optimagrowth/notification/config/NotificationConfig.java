package com.optimagrowth.notification.config;

import java.security.GeneralSecurityException;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import nl.martijndwars.webpush.PushService;

@Configuration
@ComponentScan(basePackageClasses = VapidConfig.class)
class NotificationConfig {

    @Bean
    PushService pushService(VapidConfig vapidConfig, BouncyCastleProvider bouncyCastleProvider)
            throws GeneralSecurityException {
        Security.addProvider(bouncyCastleProvider);

        return new PushService(vapidConfig.getPublicKey(), vapidConfig.getPrivateKey(), vapidConfig.getSubject());
    }

    @Bean
    BouncyCastleProvider bouncyCastleProvider() {
        return new BouncyCastleProvider();
    }

}
