package com.optimagrowth.notification.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "subscription_notification_event")
public class SubscriptionNotificationEvent {
    @Id
    @ManyToOne
    @JoinColumn(name = "subscription_id", nullable = false, updatable = false)
    private PushSubscription subscription;

    @Id
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false, updatable = false)
    private NotificationEvent event;
}
