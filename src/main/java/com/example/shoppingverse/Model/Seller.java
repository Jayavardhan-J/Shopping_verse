package com.example.shoppingverse.Model;

import com.example.shoppingverse.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "seller")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int SellerId;

    String Name;

    @Column(unique = true)
    String emailId;

    @Column(unique = true)
    String panNo;

    @Column(unique = true)
    String mobileNo;


    @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL)
    List<Product> productList= new ArrayList<>();


}
