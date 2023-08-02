package com.example.shoppingverse.Service;

import com.example.shoppingverse.DTO.RequestDTO.OrderRequestDto;
import com.example.shoppingverse.DTO.ResponseDTO.OrderResponseDto;
import com.example.shoppingverse.Enum.ProductStatus;
import com.example.shoppingverse.Exception.CustomerNotFoundException;
import com.example.shoppingverse.Exception.InsufficientQuantityException;
import com.example.shoppingverse.Exception.InvalidCardException;
import com.example.shoppingverse.Exception.ProductNotFoundException;
import com.example.shoppingverse.Model.*;
import com.example.shoppingverse.Respository.*;
import com.example.shoppingverse.Transformer.ItemTransformer;
import com.example.shoppingverse.Transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderEntityRepository orderEntityRepository;

    @Autowired
    CardService cardService;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    JavaMailSender mailSender;

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) {

        Customer customer = customerRepository.findByEmailId(orderRequestDto.getCustomerEmail());
        if(customer==null){
            throw new CustomerNotFoundException("Customer Doesn't exist");
        }

        Optional<Product> productOptional = productRepository.findById(orderRequestDto.getProductId());
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product doesn't exist");
        }

        Card card = cardRepository.findByCardNo(orderRequestDto.getCardUsed());
        Date todayDate = new Date();
        if(card==null || card.getCvv()!=orderRequestDto.getCvv() || todayDate.after(card.getValidTill())){
            throw new InvalidCardException("Invalid card");
        }

        Product product = productOptional.get();
        if(product.getAvailableQuantity() < orderRequestDto.getRequiredQuantity()){
            throw new InsufficientQuantityException("Insufficient Quantity available");
        }

        int newQuantity = product.getAvailableQuantity()- orderRequestDto.getRequiredQuantity();
        product.setAvailableQuantity(newQuantity);
        if(newQuantity==0){
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }


        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(String.valueOf(UUID.randomUUID()));
        orderEntity.setCardUsed(cardService.generateMaskedCard(orderRequestDto.getCardUsed()));
        orderEntity.setOrderTotal(orderRequestDto.getRequiredQuantity()*product.getPrice());

        Item item = ItemTransformer.ItemRequestDtoToItem(orderRequestDto.getRequiredQuantity());
        item.setOrderEntity(orderEntity);
        item.setProduct(product);

        orderEntity.setCustomer(customer);
        orderEntity.getItemList().add(item);

        OrderEntity savedOrder = orderEntityRepository.save(orderEntity);

        product.getItemList().add(savedOrder.getItemList().get(0));
        customer.getOrders().add(savedOrder);

        sendEmail(savedOrder);
        return OrderTransformer.OrderToOrderResponseDto(savedOrder);
    }

    public OrderEntity placeOrder(Cart cart, Card card) {

        OrderEntity order = new OrderEntity();
        order.setOrderId(String.valueOf(UUID.randomUUID()));
        order.setCardUsed(cardService.generateMaskedCard(card.getCardNo()));

        int orderTotal = 0;
        for(Item item: cart.getItemList()){

            Product product = item.getProduct();
            if(product.getAvailableQuantity() < item.getRequiredQuantity()){
                throw new InsufficientQuantityException("Sorry! Insufficient quatity available for: "+product.getProductName());
            }

            int newQuantity = product.getAvailableQuantity() - item.getRequiredQuantity();
            product.setAvailableQuantity(newQuantity);
            if(newQuantity==0){
                product.setProductStatus(ProductStatus.OUT_OF_STOCK);
            }

            orderTotal += product.getPrice()*item.getRequiredQuantity();
            item.setOrderEntity(order);
        }

        order.setOrderTotal(orderTotal);
        order.setItemList(cart.getItemList());
        order.setCustomer(card.getCustomer());

        return order;
    }
    public void sendEmail(OrderEntity orderEntity){
        String text= "Congrats! Your order has been placed.'\n' "+
                "Order id : " + orderEntity.getOrderId()+'\n'+
                "Order total : "+orderEntity.getOrderTotal()+'\n'+
                "Order date : " +orderEntity.getOrderDate()+'\n';
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(orderEntity.getCustomer().getEmailId());
        mail.setFrom("shoppingverse1920@gmail.com");
        mail.setSubject("Order Placed");
        mail.setText(text);
        mailSender.send(mail);
    }

//    public List<OrderEntity> getTopFiveOrdersByPrice() throws Exception {
//        List<OrderEntity> top = orderEntityRepository.getTopFiveOrdersByPrice();
//        if(top.size()<5)throw new Exception("Orders are less than 5");
//
//        return top;
//    }
}
