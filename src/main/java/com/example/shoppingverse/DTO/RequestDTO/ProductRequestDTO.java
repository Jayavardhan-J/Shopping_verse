package com.example.shoppingverse.DTO.RequestDTO;

import com.example.shoppingverse.Enum.ProductCategory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDTO {
    String sellerEmail;

    String productName;

    int price;

    int availableQuantity;

    ProductCategory category;
}
