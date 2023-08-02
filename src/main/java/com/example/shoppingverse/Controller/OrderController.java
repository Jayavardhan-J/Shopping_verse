package com.example.shoppingverse.Controller;

import com.example.shoppingverse.DTO.RequestDTO.OrderRequestDto;
import com.example.shoppingverse.DTO.ResponseDTO.OrderResponseDto;
import com.example.shoppingverse.Model.OrderEntity;
import com.example.shoppingverse.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity placeOrder(@RequestBody OrderRequestDto orderRequestDto){

        try{
            OrderResponseDto response = orderService.placeOrder(orderRequestDto);
            return new ResponseEntity(response, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
//    @GetMapping("/get-top-5-orders-by-price")
//    public ResponseEntity getTopFiveOrdersByPrice(){
//        try{
//            List<OrderEntity> orderResponseDto =orderService.getTopFiveOrdersByPrice();
//            return new ResponseEntity(orderResponseDto,HttpStatus.FOUND);
//        }
//        catch(Exception e){
//            return new ResponseEntity(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
//        }
//    }
}
