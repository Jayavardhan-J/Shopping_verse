package com.example.shoppingverse.Controller;

import com.example.shoppingverse.DTO.RequestDTO.SellerRequestDTO;
import com.example.shoppingverse.DTO.ResponseDTO.SellerResponseDTO;
import com.example.shoppingverse.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class ServiceController {
    @Autowired
    SellerService sellerService;

    @PostMapping("/add_seller")
    public ResponseEntity addSeller(@RequestBody SellerRequestDTO sellerRequestDTO){
        SellerResponseDTO sellerResponseDTO = sellerService.addSeller(sellerRequestDTO);
        return new ResponseEntity(sellerResponseDTO, HttpStatus.CREATED);
    }
}
