package com.example.shoppingverse.DTO.RequestDTO;

import com.example.shoppingverse.Enum.CardType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardRequestDTO {
    String cardNo;

    String customerMobileNo;

    Date validTill;

    int cvv;
    CardType cardType;
}
