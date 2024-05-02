package com.optimagrowth.notification.service;

import java.security.GeneralSecurityException;

import com.optimagrowth.notification.model.PushSubscription;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import nl.martijndwars.webpush.Notification;

public interface NotificationService {
    PushSubscription subscribe(@Valid @NotNull PushSubscription subscription);

    void send(Notification notification) throws GeneralSecurityException;
}
