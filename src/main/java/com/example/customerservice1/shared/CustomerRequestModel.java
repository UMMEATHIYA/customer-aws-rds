package com.example.customerservice1.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CustomerRequestModel {
    private String firstName;
    private String lastName;
    private double amount;
}