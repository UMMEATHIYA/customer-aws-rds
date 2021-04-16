package com.example.customerservice1.service;

import com.example.customerservice1.dao.CustomerRepository;
import com.example.customerservice1.exception.CustomerNotFoundException;
import com.example.customerservice1.model.CustomerModel;
import com.example.customerservice1.shared.CustomerRequestModel;
import com.example.customerservice1.shared.CustomerResponseModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService{
    private ModelMapper modelMapper;
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(ModelMapper modelMapper, CustomerRepository customerRepository) {
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponseModel createCustomer(CustomerRequestModel customerDetails) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CustomerModel model=modelMapper.map(customerDetails, CustomerModel.class);
        model.setCustomerId(UUID.randomUUID().toString());
        CustomerModel response=customerRepository.save(model);
        return modelMapper.map(response,CustomerResponseModel.class);
    }

    @Override
    public List<CustomerModel> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerResponseModel findByCustomerId(String customerId) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CustomerModel model = customerRepository.findByCustomerId(customerId);
        if(model==null) {
            return null;
        }else {
            CustomerModel dto = mapper.map(model, CustomerModel.class);
            return modelMapper.map(dto,CustomerResponseModel.class);
        }
    }

    @Override
    public CustomerResponseModel findByAmount(Double amount) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CustomerModel model=customerRepository.findByAmount(amount);
        System.out.println(model);
        return (mapper.map(model, CustomerResponseModel.class));
    }

    @Override
    public List<CustomerModel> getCustomerByFirstName(String firstName) {
        List<CustomerModel> customers=customerRepository.getCustomerByFirstName(firstName);
        return customers;
    }

    @Override
    public CustomerResponseModel updateCustomer(String customerId, CustomerRequestModel customerDto) {
        CustomerModel customerModel=customerRepository.findByCustomerId(customerId);
        if(customerModel==null)
        {
            throw new CustomerNotFoundException("no such customer found");
        }
        ModelMapper mapper=new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CustomerModel model=mapper.map(customerDto, CustomerModel.class);
        customerModel.setFirstName(model.getFirstName());
        customerModel.setLastName(model.getLastName());
        customerModel.setAmount(model.getAmount());
        customerRepository.save(customerModel);
        CustomerResponseModel cusDto=mapper.map(customerModel, CustomerResponseModel.class);
        return cusDto;
    }

    @Override
    public Integer deleteByCustomerId(String customerId) {
        return customerRepository.deleteByCustomerId(customerId);
    }

    @Override
    public List<CustomerModel> getCustomerByLastName(String lastName) {
        List<CustomerModel> customers=customerRepository.getCustomerByFirstName(lastName);
        return customers;
    }
}