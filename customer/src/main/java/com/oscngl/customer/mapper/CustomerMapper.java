package com.oscngl.customer.mapper;

import com.oscngl.customer.model.Customer;
import com.oscngl.customer.request.CustomerRequest;
import com.oscngl.customer.response.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer requestToCustomer(CustomerRequest customersRequest);
    CustomerRequest customerToRequest(Customer customer);
    List<Customer> requestsToCustomers(List<CustomerRequest> customerRequests);
    List<CustomerRequest> customersToRequests(List<Customer> customers);

    Customer responseToCustomer(CustomerResponse customerResponse);
    CustomerResponse customerToResponse(Customer customer);
    List<Customer> responsesToCustomer(List<CustomerResponse> customerResponses);
    List<CustomerResponse> customersToResponses(List<Customer> customers);

}
