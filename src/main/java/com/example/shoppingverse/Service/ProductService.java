package com.example.shoppingverse.Service;

import com.example.shoppingverse.DTO.RequestDTO.ProductRequestDTO;
import com.example.shoppingverse.DTO.ResponseDTO.ProductResponseDTO;
import com.example.shoppingverse.Enum.ProductCategory;
import com.example.shoppingverse.Enum.ProductStatus;
import com.example.shoppingverse.Exception.SellerNotFoundException;
import com.example.shoppingverse.Model.Product;
import com.example.shoppingverse.Model.Seller;
import com.example.shoppingverse.Respository.ProductRepository;
import com.example.shoppingverse.Respository.SellerRepository;
import com.example.shoppingverse.Transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    SellerRepository sellerRepository;

    public ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO) {
        Seller seller = sellerRepository.findByEmailId(productRequestDTO.getSellerEmail());
        if(seller==null)throw new SellerNotFoundException("Seller not found");
        Product product= ProductTransformer.productRequestDtoTOProduct(productRequestDTO);
        product.setProductStatus(ProductStatus.AVAILABLE);
        product.setSeller(seller);
        seller.getProductList().add(product);
        seller=sellerRepository.save(seller);
        List<Product> productList = seller.getProductList();
        return ProductTransformer.productToProductResponseDto(productList.get(productList.size()-1));

    }
    public List<ProductResponseDTO> getProdByCategoryAndPriceGreaterThan(int price, ProductCategory category){
        List<Product> productList=productRepository.getProdByCategoryAndPriceGreaterThan(price,category);

        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        for(Product product:productList)productResponseDTOS.add(ProductTransformer.productToProductResponseDto(product));
        return productResponseDTOS;
    }
}
