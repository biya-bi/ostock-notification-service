package com.optimagrowth.notification.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "push_subscription")
public class PushSubscription {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "endpoint", nullable = false)
    private String endpoint;

    @Column(name = "expiration_time")
    private Long expirationTime;

    @NotNull
    @Embedded
    private SubscriptionKeys keys;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subscription")
    private List<SubscriptionNotificationEvent> events;
}
