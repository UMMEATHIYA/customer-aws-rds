package com.example.customerservice1.service;

import com.example.customerservice1.model.CustomerModel;
import com.example.customerservice1.shared.CustomerRequestModel;
import com.example.customerservice1.shared.CustomerResponseModel;

import java.util.List;

public interface CustomerService {
    public CustomerResponseModel createCustomer(CustomerRequestModel customerDetails);
    public List<CustomerModel> getAllCustomer();
    public CustomerResponseModel findByCustomerId(String customerId);
    public CustomerResponseModel findByAmount(Double amount);
    public List<CustomerModel> getCustomerByFirstName(String firstName);
    public CustomerResponseModel updateCustomer(String customerId, CustomerRequestModel customerDto);
    public Integer deleteByCustomerId(String customerId);
    public List<CustomerModel> getCustomerByLastName(String lastName);

}