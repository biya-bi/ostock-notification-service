package com.optimagrowth.notification.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.optimagrowth.notification.model.NotificationSubscription;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Repository
@CircuitBreaker(name = "notificationSubscriptionRepository")
public interface NotificationSubscriptionRepository extends CrudRepository<NotificationSubscription, String> {
}
