package com.example.shoppingverse.Service;

import com.example.shoppingverse.DTO.RequestDTO.SellerRequestDTO;
import com.example.shoppingverse.DTO.ResponseDTO.SellerResponseDTO;
import com.example.shoppingverse.Model.Seller;
import com.example.shoppingverse.Respository.SellerRepository;
import com.example.shoppingverse.Transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;
    public SellerResponseDTO addSeller(SellerRequestDTO sellerRequestDTO){
        Seller seller = SellerTransformer.sellerRequestDTOToSeller(sellerRequestDTO);
        Seller savedSeller = sellerRepository.save(seller);
        return SellerTransformer.sellerToSellerResponseDTO(savedSeller);
    }
}
