package com.example.customerservice1.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseModel {
    private String customerId;
    private String firstName;
    private String lastName;
    private double amount;
}
