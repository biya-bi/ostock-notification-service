package com.optimagrowth.notification.service;

import java.security.GeneralSecurityException;

import com.optimagrowth.notification.model.NotificationSubscription;

import nl.martijndwars.webpush.Notification;

public interface NotificationService {
    NotificationSubscription subscribe(NotificationSubscription subscription);

    void send(Notification notification) throws GeneralSecurityException;
}
