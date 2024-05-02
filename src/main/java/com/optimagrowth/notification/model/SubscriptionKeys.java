
package com.optimagrowth.notification.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Embeddable
public class SubscriptionKeys {

    @Column(name = "p256dh", nullable = false)
    @NotBlank
    private String p256dh;

    @Column(name = "auth", nullable = false)
    @NotBlank
    private String auth;

}
