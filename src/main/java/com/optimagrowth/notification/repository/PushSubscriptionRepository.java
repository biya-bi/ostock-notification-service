package com.optimagrowth.notification.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.optimagrowth.notification.model.PushSubscription;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Repository
@CircuitBreaker(name = "pushSubscriptionRepository")
public interface PushSubscriptionRepository extends CrudRepository<PushSubscription, String> {
}
