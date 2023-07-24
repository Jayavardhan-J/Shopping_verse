package com.example.shoppingverse.Respository;

import com.example.shoppingverse.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    public Customer findByMobNo(String mobileNo);
    public Customer findByEmailId(String email);
}
