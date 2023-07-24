package com.example.shoppingverse.Transformer;

import com.example.shoppingverse.DTO.RequestDTO.ProductRequestDTO;
import com.example.shoppingverse.DTO.ResponseDTO.ProductResponseDTO;
import com.example.shoppingverse.Enum.ProductStatus;
import com.example.shoppingverse.Model.Product;

public class ProductTransformer {

    public static Product productRequestDtoTOProduct(ProductRequestDTO productRequestDTO){
        return Product.builder().productName(productRequestDTO.getProductName())
                .price(productRequestDTO.getPrice())
                .availableQuantity(productRequestDTO.getAvailableQuantity())
                .category(productRequestDTO.getCategory())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

    public static ProductResponseDTO productToProductResponseDto(Product product){
        return ProductResponseDTO.builder().productName(product.getProductName())
                .sellerName(product.getSeller().getName())
                .productStatus(product.getProductStatus())
                .price(product.getPrice())
                .category(product.getCategory())
                .availableQuantity(product.getAvailableQuantity())
                .build();
    }
}
