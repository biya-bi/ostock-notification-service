package com.optimagrowth.notification.service;

import com.optimagrowth.notification.model.NotificationSubscription;

public interface NotificationService {
    NotificationSubscription subscribe(NotificationSubscription subscription);
}
