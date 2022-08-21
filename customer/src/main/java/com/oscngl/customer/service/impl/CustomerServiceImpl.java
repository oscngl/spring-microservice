package com.oscngl.customer.service.impl;

import com.oscngl.customer.exception.EntityAlreadyExistsException;
import com.oscngl.customer.exception.EntityNotFoundException;
import com.oscngl.customer.mapper.CustomerMapper;
import com.oscngl.customer.model.Customer;
import com.oscngl.customer.repository.CustomerRepository;
import com.oscngl.customer.request.CustomerRequest;
import com.oscngl.customer.response.CustomerResponse;
import com.oscngl.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

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
        return CustomerMapper.INSTANCE
                .customerToResponse(customerRepository.save(CustomerMapper.INSTANCE
                        .requestToCustomer(customerRequest)));
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
