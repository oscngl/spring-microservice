package com.oscngl.subscription.controller;

import com.oscngl.subscription.model.Subscription;
import com.oscngl.subscription.request.SubscriptionRequest;
import com.oscngl.subscription.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping
    public ResponseEntity<List<Subscription>> getSubscriptions() {
        return new ResponseEntity<>(subscriptionService.getSubscriptions(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable Long id) {
        return new ResponseEntity<>(subscriptionService.getSubscriptionById(id), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Subscription> getSubscriptionByEmail(@PathVariable("email") String customerEmail) {
        return new ResponseEntity<>(subscriptionService.getSubscriptionByEmail(customerEmail), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Subscription> createSubscription(@Valid @RequestBody SubscriptionRequest subscriptionRequest) {
        return new ResponseEntity<>(subscriptionService.createSubscription(subscriptionRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteSubscriptionByEmail(@PathVariable("email") String customerEmail) {
        subscriptionService.deleteSubscriptionByEmail(customerEmail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/subscribe/{email}")
    public ResponseEntity<Boolean> isSubscribe(@PathVariable("email") String customerEmail) {
        return new ResponseEntity<>(subscriptionService.isSubscribe(customerEmail), HttpStatus.OK);
    }

}
