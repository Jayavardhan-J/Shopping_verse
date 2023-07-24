package com.example.shoppingverse.DTO.RequestDTO;

import com.example.shoppingverse.Enum.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SellerRequestDTO {
    String Name;


    String emailId;


    String panNo;


    String mobileNo;


}
