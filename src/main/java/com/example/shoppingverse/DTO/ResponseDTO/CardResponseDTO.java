package com.example.shoppingverse.DTO.ResponseDTO;
import java.util.Date;

import com.example.shoppingverse.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardResponseDTO {
    String customerName;

    String cardNo;

    Date validTill;

    CardType cardType;

}
