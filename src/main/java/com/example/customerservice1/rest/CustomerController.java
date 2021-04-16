package com.example.customerservice1.rest;

import com.example.customerservice1.model.CustomerModel;
import com.example.customerservice1.service.CustomerService;
import com.example.customerservice1.shared.CustomerRequestModel;
import com.example.customerservice1.shared.CustomerResponseModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }



    @PostMapping("/customers")
    public ResponseEntity<CustomerResponseModel> createCustomer(@RequestBody CustomerRequestModel customerRequestModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customerRequestModel));

    }

    @GetMapping("/customer/list")
    public List<CustomerModel> getAllCustomer() {
        ModelMapper mapper = new ModelMapper();
        return customerService.getAllCustomer().stream().map(post-> mapper.map(post, CustomerModel.class)).collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<CustomerResponseModel> getCustomerByCustomerId(@PathVariable("customerId") String customerId)
    {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CustomerResponseModel dto = customerService.findByCustomerId(customerId);
        CustomerResponseModel response=mapper.map(dto, CustomerResponseModel.class);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/customer/amount/{amount}")
    public ResponseEntity<CustomerResponseModel> findCustomerByAmount(@PathVariable("amount") Double amount)
    {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CustomerResponseModel dto=customerService.findByAmount(amount);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/customer/firstName/{firstName}")
    public ResponseEntity<List<CustomerResponseModel>> getByfirstName(@PathVariable("firstName") String firstName)
    {
        List<CustomerModel> list=customerService.getCustomerByFirstName(firstName);
        List<CustomerResponseModel> responses=new ArrayList<CustomerResponseModel>();
        Iterator<CustomerModel> i=list.iterator();
        while(i.hasNext())
        {
            CustomerModel m=i.next();
            CustomerResponseModel model=new CustomerResponseModel();
            model.setCustomerId(m.getCustomerId());
            model.setAmount(m.getAmount());
            model.setFirstName(m.getFirstName());
            model.setLastName(m.getLastName());
            responses.add(model);
        }
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/customer/{customerid}")
    public ResponseEntity<CustomerResponseModel> updateCustomer(@PathVariable("customerid") String customerId,
                                                                @RequestBody CustomerRequestModel requestModel)
    {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CustomerRequestModel dto= mapper.map(requestModel, CustomerRequestModel.class);
        CustomerResponseModel d= customerService.updateCustomer(customerId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(d);
    }

    @DeleteMapping("/customer/delete/{customerId}")
    public ResponseEntity<Integer> deleteCustomer(@PathVariable("customerId") String customerId)
    {
        Integer result=customerService.deleteByCustomerId(customerId);
        return ResponseEntity.ok(result);

    }

    @GetMapping("/customer/lastName/{lastName}")
    public ResponseEntity<List<CustomerResponseModel>> getBylastName(@PathVariable("lastName") String lastName)
    {
        List<CustomerModel> list=customerService.getCustomerByFirstName(lastName);
        List<CustomerResponseModel> responses=new ArrayList<CustomerResponseModel>();
        Iterator<CustomerModel> i=list.iterator();
        while(i.hasNext())
        {
            CustomerModel m=i.next();
            CustomerResponseModel model=new CustomerResponseModel();
            model.setCustomerId(m.getCustomerId());
            model.setAmount(m.getAmount());
            model.setFirstName(m.getFirstName());
            model.setLastName(m.getLastName());
            responses.add(model);
        }
        return ResponseEntity.ok(responses);
    }
}