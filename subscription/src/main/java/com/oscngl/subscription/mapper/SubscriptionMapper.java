package com.oscngl.subscription.mapper;

import com.oscngl.subscription.model.Subscription;
import com.oscngl.subscription.request.SubscriptionRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubscriptionMapper {

    SubscriptionMapper INSTANCE = Mappers.getMapper(SubscriptionMapper.class);

    Subscription requestToSubscription(SubscriptionRequest subscriptionRequest);
    SubscriptionRequest subscriptionToRequest(Subscription subscription);
    List<Subscription> requestsToSubscriptions(List<SubscriptionRequest> subscriptionRequests);
    List<SubscriptionRequest> subscriptionsToRequests(List<Subscription> subscriptions);

}
