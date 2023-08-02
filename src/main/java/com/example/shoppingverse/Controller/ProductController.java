package com.example.shoppingverse.Controller;

import com.example.shoppingverse.DTO.RequestDTO.ProductRequestDTO;
import com.example.shoppingverse.DTO.ResponseDTO.ProductResponseDTO;
import com.example.shoppingverse.Enum.ProductCategory;
import com.example.shoppingverse.Exception.SellerNotFoundException;
import com.example.shoppingverse.Model.Product;
import com.example.shoppingverse.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping("/add_product")
    public ResponseEntity addProduct(@RequestBody ProductRequestDTO productRequestDTO){
        try {
            ProductResponseDTO productResponseDTO = productService.addProduct(productRequestDTO);
            return new ResponseEntity(productResponseDTO, HttpStatus.CREATED);
        }
        catch (SellerNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get_product_by_price_and_category")
    public ResponseEntity getProdByCategoryAndPriceGreaterThan(@RequestParam("category") ProductCategory category, @RequestParam("price") int price){
        List<ProductResponseDTO> productResponseDTOS = productService.getProdByCategoryAndPriceGreaterThan(price,category);
        return new ResponseEntity(productResponseDTOS,HttpStatus.FOUND);
    }
    @GetMapping("/get_products_by_lowest_value")
    public List<Product> getProductsByLowestValue(@RequestParam ProductCategory productCategory){
        return productService.getProductsByLowestValue(productCategory);
    }
}
