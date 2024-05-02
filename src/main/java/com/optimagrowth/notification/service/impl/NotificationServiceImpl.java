package com.optimagrowth.notification.service.impl;

import java.security.GeneralSecurityException;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.optimagrowth.notification.config.VapidConfig;
import com.optimagrowth.notification.model.PushSubscription;
import com.optimagrowth.notification.repository.PushSubscriptionRepository;
import com.optimagrowth.notification.service.NotificationService;

import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;

@Service
class NotificationServiceImpl implements NotificationService {

    private final PushSubscriptionRepository subscriptionRepository;
    private final VapidConfig vapidConfig;

    NotificationServiceImpl(PushSubscriptionRepository subscriptionRepository, VapidConfig config) {
        this.subscriptionRepository = subscriptionRepository;
        this.vapidConfig = config;
    }

    @Override
    public PushSubscription subscribe(PushSubscription subscription) {
        subscription.setId(UUID.randomUUID().toString());

        return subscriptionRepository.save(subscription);
    }

    @Override
    public void send(Notification notification) throws GeneralSecurityException {
        PushService pushService = new PushService(vapidConfig.getPublicKey(), vapidConfig.getPrivateKey(),
                vapidConfig.getSubject());
        // TODO: Get subscriptionJson from DB
        // Subscription subscription = new Gson().fromJson("subscriptionJson",
        // Subscription.class);
        // Notification notification = new Notification(subscription, "payload");
        // HttpResponse httpResponse = pushService.send(notification);
    }

}
