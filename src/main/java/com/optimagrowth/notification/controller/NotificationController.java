package com.optimagrowth.notification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optimagrowth.notification.model.PushSubscription;
import com.optimagrowth.notification.service.NotificationService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/v1/notification")
class NotificationController {

    private final NotificationService notificationService;

    NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/subscribe")
    ResponseEntity<Void> subscribe(@Valid @NotNull @RequestBody PushSubscription subscription) {
        notificationService.subscribe(subscription);

        return ResponseEntity.ok().build();
    }

}
