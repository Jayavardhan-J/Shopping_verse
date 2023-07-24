package com.example.shoppingverse.Service;

import com.example.shoppingverse.DTO.RequestDTO.CheckoutCartRequestDto;
import com.example.shoppingverse.DTO.RequestDTO.ItemRequestDto;
import com.example.shoppingverse.DTO.ResponseDTO.CartResponseDto;
import com.example.shoppingverse.DTO.ResponseDTO.OrderResponseDto;
import com.example.shoppingverse.Exception.CustomerNotFoundException;
import com.example.shoppingverse.Exception.EmptyCartException;
import com.example.shoppingverse.Exception.InvalidCardException;
import com.example.shoppingverse.Model.*;
import com.example.shoppingverse.Respository.*;
import com.example.shoppingverse.Transformer.CartTransformer;

import com.example.shoppingverse.Transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class CartService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderService orderService;
    @Autowired
    private OrderEntityRepository orderEntityRepository;

    public CartResponseDto addItemToCart(ItemRequestDto itemRequestDto,Item item) {

        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmail());
        Product product = productRepository.findById(itemRequestDto.getProductId()).get();

        Cart cart = customer.getCart();
        cart.setCartTotal(cart.getCartTotal() + product.getPrice()*itemRequestDto.getRequiredQuantity());

        item.setCart(cart);
        item.setProduct(product);
        Item savedItem = itemRepository.save(item);

        cart.getItemList().add(savedItem);
        product.getItemList().add(savedItem);
        Cart savedCart = cartRepository.save(cart);
        productRepository.save(product);


        return CartTransformer.CartToCartReponseDto(savedCart);

    }

    public OrderResponseDto checkoutCart(CheckoutCartRequestDto checkoutCartRequestDto) {

        Customer customer = customerRepository.findByEmailId(checkoutCartRequestDto.getCustomerEmail());
        if(customer==null){
            throw new CustomerNotFoundException("Customer doesn't exist");
        }

        Card card = cardRepository.findByCardNo(checkoutCartRequestDto.getCardNo());
        Date currentDate = new Date();
        if(card==null || card.getCvv()!= checkoutCartRequestDto.getCvv() || currentDate.after(card.getValidTill())){
            throw new InvalidCardException("Card is not valid");
        }

        Cart cart = customer.getCart();
        if(cart.getItemList().size()==0){
            throw new EmptyCartException("Sorry! The cart is empty");
        }

        OrderEntity order = orderService.placeOrder(cart,card);
        resetCart(cart);

        OrderEntity savedOrder = orderEntityRepository.save(order);


        return OrderTransformer.OrderToOrderResponseDto(savedOrder);
    }

    public void resetCart(Cart cart){

        cart.setCartTotal(0);
        for(Item item: cart.getItemList()){
            item.setCart(null);
        }
        cart.setItemList(new ArrayList<>());

    }
}
