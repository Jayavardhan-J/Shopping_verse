package com.example.shoppingverse.Respository;

import com.example.shoppingverse.Enum.ProductCategory;
import com.example.shoppingverse.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query("select p from Product p where p.price >= :price and p.category = :category")
    public List<Product> getProdByCategoryAndPriceGreaterThan(int price, ProductCategory category);

    // value = " select * from product where category=:category order by price limit 5",nativeQuery = true
    @Query("select p from Product p where p.category =:category order by p.price")
    List<Product> getProductsByLowestValue(ProductCategory category);
}
