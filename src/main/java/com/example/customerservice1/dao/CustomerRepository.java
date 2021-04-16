package com.example.customerservice1.dao;

import com.example.customerservice1.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel,Integer> {
    @Query
    public CustomerModel findByCustomerId(String customerId);
    @Query
    public CustomerModel findByAmount(Double amount);
    @Query
    public List<CustomerModel> getCustomerByFirstName(String firstName);
    @Query
    public Integer deleteByCustomerId(String customerId);
    @Query
    public List<CustomerModel> getCustomerByLastName(String lastName);

}
