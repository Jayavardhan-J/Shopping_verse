package com.example.shoppingverse.DTO.RequestDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CheckoutCartRequestDto {

    String customerEmail;

    String cardNo;

    int cvv;
}