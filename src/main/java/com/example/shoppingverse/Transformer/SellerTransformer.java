package com.example.shoppingverse.Transformer;

import com.example.shoppingverse.DTO.RequestDTO.SellerRequestDTO;
import com.example.shoppingverse.DTO.ResponseDTO.SellerResponseDTO;
import com.example.shoppingverse.Model.Seller;

public class SellerTransformer {
    public static Seller sellerRequestDTOToSeller(SellerRequestDTO sellerRequestDTO){
        return Seller.builder().Name(sellerRequestDTO.getName())
                .panNo(sellerRequestDTO.getPanNo())
                .emailId(sellerRequestDTO.getEmailId())
                .mobileNo(sellerRequestDTO.getMobileNo())
                .build();
    }
    public static SellerResponseDTO sellerToSellerResponseDTO(Seller seller){
        return SellerResponseDTO.builder().Name(seller.getName())
                .emailId(seller.getEmailId())
                .mobileNo(seller.getMobileNo())
                .build();
    }
}
