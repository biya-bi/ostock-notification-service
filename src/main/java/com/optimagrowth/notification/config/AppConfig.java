package com.optimagrowth.notification.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.optimagrowth.config.CrossCuttingConcernsConfig;

@Configuration
@ComponentScan(basePackageClasses = { CrossCuttingConcernsConfig.class, VapidConfig.class })
class AppConfig {
}
