package com.example.shoppingverse.DTO.RequestDTO;

import com.example.shoppingverse.Enum.Gender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRequestDTO {
    String name;

    String emailId;

    String mobileNo;

    Gender gender;
}
