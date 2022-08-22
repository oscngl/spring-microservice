package com.oscngl.subscription.service;

import com.oscngl.subscription.model.Subscription;
import com.oscngl.subscription.request.SubscriptionRequest;

import java.util.List;

public interface SubscriptionService {

    List<Subscription> getSubscriptions();
    Subscription getSubscriptionById(Long id);
    Subscription getSubscriptionByEmail(String customerEmail);
    Subscription createSubscription(SubscriptionRequest subscriptionRequest);
    void deleteSubscriptionByEmail(String customerEmail);

}
