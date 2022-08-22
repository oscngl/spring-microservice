package com.oscngl.subscription.service.impl;

import com.oscngl.subscription.exception.EntityAlreadyExistsException;
import com.oscngl.subscription.exception.EntityNotFoundException;
import com.oscngl.subscription.mapper.SubscriptionMapper;
import com.oscngl.subscription.model.Subscription;
import com.oscngl.subscription.repository.SubscriptionRepository;
import com.oscngl.subscription.request.SubscriptionRequest;
import com.oscngl.subscription.service.SequenceGeneratorService;
import com.oscngl.subscription.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public List<Subscription> getSubscriptions() {
        return subscriptionRepository.findAll();
    }

    @Override
    public Subscription getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subscription not found with id: " + id));
    }

    @Override
    public Subscription getSubscriptionByEmail(String customerEmail) {
        return subscriptionRepository.findByCustomerEmail(customerEmail)
                .orElseThrow(() -> new EntityNotFoundException("Subscription not found with email: " + customerEmail));
    }

    @Override
    public Subscription createSubscription(SubscriptionRequest subscriptionRequest) {
        Optional<Subscription> subscriptionByEmail = subscriptionRepository.findByCustomerEmail(subscriptionRequest.getCustomerEmail());
        if(subscriptionByEmail.isPresent()) {
            throw new EntityAlreadyExistsException("Customer already subscribed with email: " + subscriptionRequest.getCustomerEmail());
        }
        Subscription subscription = SubscriptionMapper.INSTANCE.requestToSubscription(subscriptionRequest);
        subscription.setId(sequenceGeneratorService.generateSequence(Subscription.SEQUENCE_NAME));
        return subscriptionRepository.save(subscription);
    }

    @Override
    public void deleteSubscriptionByEmail(String customerEmail) {
        Optional<Subscription> subscriptionByEmail = subscriptionRepository.findByCustomerEmail(customerEmail);
        if (subscriptionByEmail.isEmpty()) {
            throw new EntityNotFoundException("Subscription not found with email: " + customerEmail);
        }
        subscriptionRepository.deleteById(subscriptionByEmail.get().getId());
    }

    @Override
    public boolean isSubscribe(String customerEmail) {
        Optional<Subscription> subscriptionByEmail = subscriptionRepository.findByCustomerEmail(customerEmail);
        if(subscriptionByEmail.isPresent()) {
            return true;
        }
        SubscriptionRequest request = new SubscriptionRequest(customerEmail);
        createSubscription(request);
        return true;
    }

}
