package com.oscngl.clients.subscription;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "subscription",
        url = "${clients.subscription.url}"
)
public interface SubscriptionClient {

    @PostMapping(path = "api/v1/subscriptions/subscribe/{email}")
    boolean isSubscribe(@PathVariable("email") String customerEmail);

}
