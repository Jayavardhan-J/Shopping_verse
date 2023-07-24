package com.example.shoppingverse.Transformer;

import com.example.shoppingverse.DTO.ResponseDTO.ItemResponseDto;
import com.example.shoppingverse.DTO.ResponseDTO.OrderResponseDto;
import com.example.shoppingverse.Model.Item;
import com.example.shoppingverse.Model.OrderEntity;

import java.util.ArrayList;
import java.util.List;

public class OrderTransformer {

    public static OrderResponseDto OrderToOrderResponseDto(OrderEntity order){

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item item: order.getItemList()){
            itemResponseDtoList.add(ItemTransformer.ItemToItemResponseDto(item));
        }

        return OrderResponseDto.builder()
                .orderId(order.getOrderId())
                .orderDate(order.getOrderDate())
                .cardUsed(order.getCardUsed())
                .orderTotal(order.getOrderTotal())
                .customerName(order.getCustomer().getName())
                .item(itemResponseDtoList)
                .build();

    }
}
