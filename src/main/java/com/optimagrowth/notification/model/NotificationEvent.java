package com.optimagrowth.notification.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "notification_event")
public class NotificationEvent {
    @Id
    @Column(nullable = false)
    private String id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String type;

    @Column(name = "occurred_at")
    private Long occurredAt;

    @NotBlank
    @Column(nullable = false)
    private String payload;

}
