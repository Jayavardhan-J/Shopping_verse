package com.example.shoppingverse.Controller;

import com.example.shoppingverse.DTO.RequestDTO.CustomerRequestDTO;
import com.example.shoppingverse.DTO.ResponseDTO.CustomerResponseDTO;
import com.example.shoppingverse.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequestDTO customerRequestDTO){
        CustomerResponseDTO response = customerService.addCustomer(customerRequestDTO);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}
