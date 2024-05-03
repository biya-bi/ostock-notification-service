package com.optimagrowth.notification.service.impl;

import java.security.Security;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;

import com.optimagrowth.notification.exception.NotificationException;
import com.optimagrowth.notification.model.PushSubscription;
import com.optimagrowth.notification.repository.PushSubscriptionRepository;
import com.optimagrowth.notification.service.NotificationService;

import jakarta.annotation.PostConstruct;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;

@Service
class NotificationServiceImpl implements NotificationService {

    private final PushSubscriptionRepository subscriptionRepository;
    private final PushService pushService;

    NotificationServiceImpl(PushSubscriptionRepository subscriptionRepository, PushService pushService) {
        this.subscriptionRepository = subscriptionRepository;
        this.pushService = pushService;
    }

    @PostConstruct
    private void init() {
        Security.addProvider(new BouncyCastleProvider());
    }

    @Override
    public PushSubscription subscribe(PushSubscription subscription) {
        subscription.setId(UUID.randomUUID().toString());

        return subscriptionRepository.save(subscription);
    }

    @Override
    public HttpResponse send(PushSubscription subscription, String payload) {
        try {
            var notification = new Notification(convert(subscription), payload);
            return pushService.send(notification);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new NotificationException(e);
        } catch (Exception e) {
            throw new NotificationException(e);
        }
    }

    private Subscription convert(PushSubscription subscription) {
        var keys = subscription.getKeys();

        var sub = new Subscription();
        sub.keys = sub.new Keys(keys.getP256dh(), keys.getAuth());
        sub.endpoint = subscription.getEndpoint();

        return sub;
    }

}
