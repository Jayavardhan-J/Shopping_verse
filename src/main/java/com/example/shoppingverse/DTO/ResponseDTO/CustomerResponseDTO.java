package com.example.shoppingverse.DTO.ResponseDTO;

import com.example.shoppingverse.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerResponseDTO {
    String name;

    String emailId;

    String mobileNo;

    Gender gender;
}
