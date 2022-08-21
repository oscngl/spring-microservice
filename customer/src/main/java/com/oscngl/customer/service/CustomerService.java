package com.oscngl.customer.service;

import com.oscngl.customer.request.CustomerRequest;
import com.oscngl.customer.response.CustomerResponse;

import java.util.List;

public interface CustomerService {

    List<CustomerResponse> getCustomers();
    CustomerResponse getCustomerById(Long id);
    CustomerResponse getCustomerByEmail(String email);
    CustomerResponse createCustomer(CustomerRequest customerRequest);
    void updateCustomer(Long id, CustomerRequest newCustomer);

}
