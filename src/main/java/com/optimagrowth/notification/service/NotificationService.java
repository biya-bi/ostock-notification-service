package com.optimagrowth.notification.service;

import org.apache.http.HttpResponse;

import com.optimagrowth.notification.model.NotificationEvent;
import com.optimagrowth.notification.model.PushSubscription;

public interface NotificationService {
    PushSubscription subscribe(PushSubscription subscription);

    NotificationEvent register(NotificationEvent event);

    HttpResponse send(PushSubscription subscription, String payload);

}
