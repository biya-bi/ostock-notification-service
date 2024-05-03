package com.optimagrowth.notification.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.optimagrowth.notification.model.PushSubscription;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Repository
@CircuitBreaker(name = "pushSubscriptionRepository")
public interface PushSubscriptionRepository extends CrudRepository<PushSubscription, String> {

    @Query("SELECT s FROM PushSubscription s WHERE s.id NOT IN :subscriptionIds")
    List<PushSubscription> findExcept(@Param("subscriptionIds") Collection<String> subscriptionIds);

}
