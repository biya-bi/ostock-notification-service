package com.optimagrowth.notification.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.optimagrowth.notification.model.NotificationEvent;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Repository
@CircuitBreaker(name = "notificationEventRepository")
public interface NotificationEventRepository extends CrudRepository<NotificationEvent, String> {
    NotificationEvent findByType(String type);
}
