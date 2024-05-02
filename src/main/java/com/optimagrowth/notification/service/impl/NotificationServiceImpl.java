package com.optimagrowth.notification.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.optimagrowth.notification.model.NotificationSubscription;
import com.optimagrowth.notification.repository.NotificationSubscriptionRepository;
import com.optimagrowth.notification.service.NotificationService;

@Service
class NotificationServiceImpl implements NotificationService {

    private final NotificationSubscriptionRepository subscriptionRepository;

    NotificationServiceImpl(NotificationSubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public NotificationSubscription subscribe(NotificationSubscription subscription) {
        subscription.setId(UUID.randomUUID().toString());

        return subscriptionRepository.save(subscription);
    }

}
