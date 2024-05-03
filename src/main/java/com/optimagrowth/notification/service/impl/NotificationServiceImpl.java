package com.optimagrowth.notification.service.impl;

import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.http.HttpResponse;
import org.springframework.stereotype.Service;

import com.optimagrowth.notification.exception.NotificationException;
import com.optimagrowth.notification.model.NotificationEvent;
import com.optimagrowth.notification.model.PushSubscription;
import com.optimagrowth.notification.model.SubscriptionNotificationEvent;
import com.optimagrowth.notification.repository.NotificationEventRepository;
import com.optimagrowth.notification.repository.PushSubscriptionRepository;
import com.optimagrowth.notification.repository.SubscriptionNotificationEventRepository;
import com.optimagrowth.notification.service.NotificationService;
import com.optimagrowth.service.MessageService;

import jakarta.ws.rs.NotFoundException;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;

@Service
class NotificationServiceImpl implements NotificationService {

    private static final String RESOURCE_NOT_FOUND = "resource.not.found";

    private final PushSubscriptionRepository subscriptionRepository;
    private final NotificationEventRepository notificationEventRepository;
    private final SubscriptionNotificationEventRepository subscriptionNotificationEventRepository;
    private final PushService pushService;
    private final MessageService messageService;

    NotificationServiceImpl(
            PushSubscriptionRepository subscriptionRepository,
            NotificationEventRepository notificationEventRepository,
            SubscriptionNotificationEventRepository subscriptionNotificationEventRepository,
            PushService pushService,
            MessageService messageService) {
        this.subscriptionRepository = subscriptionRepository;
        this.notificationEventRepository = notificationEventRepository;
        this.subscriptionNotificationEventRepository = subscriptionNotificationEventRepository;
        this.pushService = pushService;
        this.messageService = messageService;
    }

    @Override
    public PushSubscription subscribe(PushSubscription subscription) {
        subscription.setId(generateUuid());

        return subscriptionRepository.save(subscription);
    }

    @Override
    public NotificationEvent register(NotificationEvent event) {
        event.setId(generateUuid());

        return notificationEventRepository.save(event);
    }

    @Override
    public void send(String eventType) {
        var notificationEvent = notificationEventRepository.findByType(eventType);

        if (notificationEvent == null) {
            throw new NotFoundException(messageService.getMessage(RESOURCE_NOT_FOUND));
        }

        var subscriptions = subscriptionNotificationEventRepository.findByEvent(notificationEvent.getId());

        var notifiedSubscriptionIds = subscriptions.stream()
                .map(SubscriptionNotificationEvent::getSubscription)
                .map(PushSubscription::getId)
                .collect(Collectors.toList());

        subscriptionRepository.findExcept(notifiedSubscriptionIds).forEach(subscription -> {
            send(subscription, notificationEvent.getPayload());
            // At this point, the notification was sent successfully. So, mark it (by saving
            // it) as done to avoid resending the same notification to the same user.

            var subscriptionNotificationEvent = new SubscriptionNotificationEvent(generateUuid(), subscription,
                    notificationEvent);
            subscriptionNotificationEventRepository.save(subscriptionNotificationEvent);
        });
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

    private String generateUuid() {
        return UUID.randomUUID().toString();
    }

}
