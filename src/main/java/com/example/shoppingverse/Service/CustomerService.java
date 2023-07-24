package com.example.shoppingverse.Service;

import com.example.shoppingverse.DTO.RequestDTO.CustomerRequestDTO;
import com.example.shoppingverse.DTO.ResponseDTO.CustomerResponseDTO;
import com.example.shoppingverse.Model.Cart;
import com.example.shoppingverse.Model.Customer;
import com.example.shoppingverse.Respository.CustomerRepository;
import com.example.shoppingverse.Transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public CustomerResponseDTO addCustomer(CustomerRequestDTO customerRequestDTO) {

        Customer customer= CustomerTransformer.customerRequestDTOToCustomer(customerRequestDTO);

        Cart cart = new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);
        customer.setCart(cart);

        Customer saved=customerRepository.save(customer);
        return CustomerTransformer.customerToCustomerResponseDTO(saved);

    }



}
