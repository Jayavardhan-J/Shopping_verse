package com.example.shoppingverse.Transformer;

import com.example.shoppingverse.DTO.RequestDTO.CustomerRequestDTO;
import com.example.shoppingverse.DTO.ResponseDTO.CustomerResponseDTO;
import com.example.shoppingverse.Model.Customer;
import lombok.experimental.UtilityClass;


public class CustomerTransformer {
    public static Customer customerRequestDTOToCustomer(CustomerRequestDTO customerRequestDTO){
        return Customer.builder().Name(customerRequestDTO.getName())
                .gender(customerRequestDTO.getGender())
                .emailId(customerRequestDTO.getEmailId())
                .mobileNo(customerRequestDTO.getMobileNo()).build();

    }
    public static CustomerResponseDTO customerToCustomerResponseDTO(Customer customer){
        return CustomerResponseDTO.builder().name(customer.getName())
                .emailId(customer.getEmailId())
                .mobileNo(customer.getMobileNo())
                .gender(customer.getGender())
                .build();

    }
}
