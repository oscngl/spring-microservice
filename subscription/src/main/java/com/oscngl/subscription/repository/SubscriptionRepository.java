package com.oscngl.subscription.repository;

import com.oscngl.subscription.model.Subscription;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends MongoRepository<Subscription, Long> {

    Optional<Subscription> findByCustomerEmail(String customerEmail);

}
