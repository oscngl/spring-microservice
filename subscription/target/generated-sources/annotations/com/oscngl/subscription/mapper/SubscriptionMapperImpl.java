package com.oscngl.subscription.mapper;

import com.oscngl.subscription.model.Subscription;
import com.oscngl.subscription.request.SubscriptionRequest;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-22T14:18:52+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class SubscriptionMapperImpl implements SubscriptionMapper {

    @Override
    public Subscription requestToSubscription(SubscriptionRequest subscriptionRequest) {
        if ( subscriptionRequest == null ) {
            return null;
        }

        Subscription subscription = new Subscription();

        subscription.setCustomerEmail( subscriptionRequest.getCustomerEmail() );

        return subscription;
    }

    @Override
    public SubscriptionRequest subscriptionToRequest(Subscription subscription) {
        if ( subscription == null ) {
            return null;
        }

        SubscriptionRequest subscriptionRequest = new SubscriptionRequest();

        subscriptionRequest.setCustomerEmail( subscription.getCustomerEmail() );

        return subscriptionRequest;
    }

    @Override
    public List<Subscription> requestsToSubscriptions(List<SubscriptionRequest> subscriptionRequests) {
        if ( subscriptionRequests == null ) {
            return null;
        }

        List<Subscription> list = new ArrayList<Subscription>( subscriptionRequests.size() );
        for ( SubscriptionRequest subscriptionRequest : subscriptionRequests ) {
            list.add( requestToSubscription( subscriptionRequest ) );
        }

        return list;
    }

    @Override
    public List<SubscriptionRequest> subscriptionsToRequests(List<Subscription> subscriptions) {
        if ( subscriptions == null ) {
            return null;
        }

        List<SubscriptionRequest> list = new ArrayList<SubscriptionRequest>( subscriptions.size() );
        for ( Subscription subscription : subscriptions ) {
            list.add( subscriptionToRequest( subscription ) );
        }

        return list;
    }
}
