package com.example.shoppingverse.Model;

import com.example.shoppingverse.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int CustomerId;

    String Name;

    @Column(unique = true)
    String emailId;

    @Column(unique = true)
    String mobileNo;
    @Enumerated(value = EnumType.STRING)
    Gender gender;


    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<Card> ListCards = new ArrayList<>();

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<OrderEntity> orders=new ArrayList<>();

    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    Cart cart;


}
