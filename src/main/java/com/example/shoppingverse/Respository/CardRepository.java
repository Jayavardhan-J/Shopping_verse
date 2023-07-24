package com.example.shoppingverse.Respository;

import com.example.shoppingverse.Model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
    public Card findByCardNo(String cardNo);
}
