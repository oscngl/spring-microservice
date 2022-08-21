package com.oscngl.customer.controller;

import com.oscngl.customer.request.CustomerRequest;
import com.oscngl.customer.response.CustomerResponse;
import com.oscngl.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getCustomers() {
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<CustomerResponse> getCustomerByEmail(@PathVariable String email) {
        return new ResponseEntity<>(customerService.getCustomerByEmail(email), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        return new ResponseEntity<>(customerService.createCustomer(customerRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerRequest newCustomer) {
        customerService.updateCustomer(id, newCustomer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
