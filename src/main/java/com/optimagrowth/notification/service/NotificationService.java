package com.optimagrowth.notification.service;

import org.apache.http.HttpResponse;

import com.optimagrowth.notification.model.NotificationEvent;
import com.optimagrowth.notification.model.PushSubscription;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface NotificationService {
    PushSubscription subscribe(@Valid @NotNull PushSubscription subscription);

    HttpResponse send(PushSubscription subscription, String payload);

    NotificationEvent register(@Valid @NotNull NotificationEvent event);
}
