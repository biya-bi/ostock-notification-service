package com.optimagrowth.notification.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "notification_subscriptions")
public class NotificationSubscription {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "endpoint", nullable = false)
    private String endpoint;

    @Column(name = "expiration_time")
    private Long expirationTime;

    @NotNull
    @Embedded
    private NotificationKeys keys;

}
