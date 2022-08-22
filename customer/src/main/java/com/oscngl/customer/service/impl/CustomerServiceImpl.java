package com.oscngl.customer.service.impl;

import com.oscngl.amqp.RabbitMQMessageProducer;
import com.oscngl.clients.notification.NotificationRequest;
import com.oscngl.clients.subscription.SubscriptionClient;
import com.oscngl.customer.exception.EntityAlreadyExistsException;
import com.oscngl.customer.exception.EntityNotFoundException;
import com.oscngl.customer.mapper.CustomerMapper;
import com.oscngl.customer.model.Customer;
import com.oscngl.customer.repository.CustomerRepository;
import com.oscngl.customer.request.CustomerRequest;
import com.oscngl.customer.response.CustomerResponse;
import com.oscngl.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final SubscriptionClient subscriptionClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    @Override
    public List<CustomerResponse> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return CustomerMapper.INSTANCE.customersToResponses(customers);
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
        return CustomerMapper.INSTANCE.customerToResponse(customer);
    }

    @Override
    public CustomerResponse getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with email: " + email));
        return CustomerMapper.INSTANCE.customerToResponse(customer);
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Optional<Customer> customerByEmail = customerRepository.findByEmail(customerRequest.getEmail());
        if(customerByEmail.isPresent()) {
            throw new EntityAlreadyExistsException("Customer already exists with email: " + customerRequest.getEmail());
        }
        boolean response = subscriptionClient.isSubscribe(customerRequest.getEmail());
        if(!response) {
            throw new EntityNotFoundException("Customer is not subscribed with email: " + customerRequest.getEmail());
        }
        Customer customer = CustomerMapper.INSTANCE.requestToCustomer(customerRequest);
        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getEmail(),
                "Hi " + customer.getFirstName() + ", welcome to my Microservice..."
        );
        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );
        log.info("Customer created: " + customer);
        return CustomerMapper.INSTANCE.customerToResponse(customerRepository.save(customer));
    }

    @Override
    public void updateCustomer(Long id, CustomerRequest newCustomer) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
        if (!customer.getEmail().equals(newCustomer.getEmail())) {
            Optional<Customer> customerByEmail = customerRepository.findByEmail(newCustomer.getEmail());
            if (customerByEmail.isPresent()) {
                throw new EntityAlreadyExistsException("Customer already exists with email: " + newCustomer.getEmail());
            }
        }
        customer.setFirstName(newCustomer.getFirstName());
        customer.setLastName(newCustomer.getLastName());
        customer.setEmail(newCustomer.getEmail());
        customer.setPassword(newCustomer.getPassword());
        customerRepository.save(customer);
    }

}
