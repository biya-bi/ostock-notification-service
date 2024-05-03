package com.optimagrowth.notification.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.optimagrowth.notification.model.SubscriptionNotificationEvent;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Repository
@CircuitBreaker(name = "subscriptionNotificationEventRepository")
public interface SubscriptionNotificationEventRepository extends CrudRepository<SubscriptionNotificationEvent, String> {

    @Query("SELECT sne FROM SubscriptionNotificationEvent sne WHERE sne.event.id=:eventId")
    List<SubscriptionNotificationEvent> findByEvent(@Param("eventId") String eventId);

}
