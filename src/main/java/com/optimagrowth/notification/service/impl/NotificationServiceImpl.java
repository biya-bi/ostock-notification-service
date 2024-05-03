package com.optimagrowth.notification.service.impl;

import java.util.UUID;

import org.apache.http.HttpResponse;
import org.springframework.stereotype.Service;

import com.optimagrowth.notification.exception.NotificationException;
import com.optimagrowth.notification.model.NotificationEvent;
import com.optimagrowth.notification.model.PushSubscription;
import com.optimagrowth.notification.repository.NotificationEventRepository;
import com.optimagrowth.notification.repository.PushSubscriptionRepository;
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
    private final NotificationEventRepository eventRepository;
    private final PushService pushService;
    private final MessageService messageService;

    NotificationServiceImpl(
            PushSubscriptionRepository subscriptionRepository,
            NotificationEventRepository eventRepository,
            PushService pushService,
            MessageService messageService) {
        this.subscriptionRepository = subscriptionRepository;
        this.eventRepository = eventRepository;
        this.pushService = pushService;
        this.messageService = messageService;
    }

    @Override
    public PushSubscription subscribe(PushSubscription subscription) {
        subscription.setId(UUID.randomUUID().toString());

        return subscriptionRepository.save(subscription);
    }

    @Override
    public NotificationEvent register(NotificationEvent event) {
        event.setId(UUID.randomUUID().toString());

        return eventRepository.save(event);
    }

    @Override
    public HttpResponse send(String eventType) {
        var event = eventRepository.findByType(eventType);

        if (event == null) {
            throw new NotFoundException(messageService.getMessage(RESOURCE_NOT_FOUND));
        }

        // TODO Get all subscriptions for which this event has not been sent
        throw new UnsupportedOperationException("Method not fully implemented 'send'");
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
