package com.optimagrowth.notification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optimagrowth.notification.model.NotificationEvent;
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

    @PostMapping("/register")
    ResponseEntity<NotificationEvent> register(@Valid @NotNull @RequestBody NotificationEvent event) {
        var newEvent = notificationService.register(event);

        return ResponseEntity.ok(newEvent);
    }

    @PostMapping("/send/{eventType}")
    ResponseEntity<Void> send(@Valid @NotNull @PathVariable("eventType") String eventType) {
        notificationService.send(eventType);

        return ResponseEntity.ok().build();
    }

}
