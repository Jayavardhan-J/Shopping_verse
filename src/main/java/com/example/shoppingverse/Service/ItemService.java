package com.example.shoppingverse.Service;


import com.example.shoppingverse.DTO.RequestDTO.ItemRequestDto;
import com.example.shoppingverse.Exception.CustomerNotFoundException;
import com.example.shoppingverse.Exception.InsufficientQuantityException;
import com.example.shoppingverse.Exception.ProductNotFoundException;
import com.example.shoppingverse.Model.Customer;
import com.example.shoppingverse.Model.Item;
import com.example.shoppingverse.Model.Product;
import com.example.shoppingverse.Respository.CustomerRepository;
import com.example.shoppingverse.Respository.ProductRepository;
import com.example.shoppingverse.Transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;
    public Item createItem(ItemRequestDto itemRequestDto) {

        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmail());
        if(customer==null){
            throw new CustomerNotFoundException("Customer doesn't exisit");
        }

        Optional<Product> productOptional = productRepository.findById(itemRequestDto.getProductId());
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product doesn't exist");
        }

        Product product = productOptional.get();

        // check for required quantity
        if(product.getAvailableQuantity()< itemRequestDto.getRequiredQuantity()){
            throw new InsufficientQuantityException("Sorry! Required quantity not avaiable");
        }

        // create item
        Item item = ItemTransformer.ItemRequestDtoToItem(itemRequestDto.getRequiredQuantity());
        return item;
    }
}