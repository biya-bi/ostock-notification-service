package com.optimagrowth.notification.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "subscription_notification_event")
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionNotificationEvent {
    @Id
    @Column(nullable = false, updatable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "subscription_id", nullable = false, updatable = false)
    private PushSubscription subscription;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false, updatable = false)
    private NotificationEvent event;
}
