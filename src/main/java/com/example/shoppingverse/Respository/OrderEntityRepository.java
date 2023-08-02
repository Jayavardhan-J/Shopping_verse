package com.example.shoppingverse.Respository;

import com.example.shoppingverse.Model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderEntityRepository extends JpaRepository<OrderEntity,Integer> {
//    @Query("select * from OrderEntity order by orderTotal DESC LIMIT 5;")
//    public List<OrderEntity> getTopFiveOrdersByPrice();

}
