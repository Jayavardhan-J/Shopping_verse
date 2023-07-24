package com.example.shoppingverse.Transformer;



import com.example.shoppingverse.DTO.ResponseDTO.CartResponseDto;
import com.example.shoppingverse.DTO.ResponseDTO.ItemResponseDto;
import com.example.shoppingverse.Model.Cart;
import com.example.shoppingverse.Model.Item;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {

    public static CartResponseDto CartToCartReponseDto(Cart cart){

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item item: cart.getItemList()){
            itemResponseDtoList.add(ItemTransformer.ItemToItemResponseDto(item));
        }

        return CartResponseDto.builder()
                .cartTotal(cart.getCartTotal())
                .customerName(cart.getCustomer().getName())
                .items(itemResponseDtoList)
                .build();
    }
}
