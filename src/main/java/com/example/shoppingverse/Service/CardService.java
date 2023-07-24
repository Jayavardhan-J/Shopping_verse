package com.example.shoppingverse.Service;

import com.example.shoppingverse.DTO.RequestDTO.CardRequestDTO;
import com.example.shoppingverse.DTO.ResponseDTO.CardResponseDTO;
import com.example.shoppingverse.Exception.CustomerNotFoundException;
import com.example.shoppingverse.Model.Card;
import com.example.shoppingverse.Model.Customer;
import com.example.shoppingverse.Respository.CardRepository;
import com.example.shoppingverse.Respository.CustomerRepository;
import com.example.shoppingverse.Transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    CustomerRepository customerRepository;
    public CardResponseDTO addCard(CardRequestDTO cardRequestDTO) {
        Customer customer = customerRepository.findByMobNo(cardRequestDTO.getCustomerMobileNo());
        if(customer==null)throw new CustomerNotFoundException("Customer does not exist");
        Card card = CardTransformer.cardRequestDtoToCard(cardRequestDTO);
        card.setCustomer(customer);
        customer.getListCards().add(card);

        Customer saved= customerRepository.save(customer);
        List<Card> cards=saved.getListCards();
        Card latestCard = cards.get(cards.size()-1);

        CardResponseDTO cardResponseDTO = CardTransformer.cardToCardResponseDTO(latestCard);
        cardResponseDTO.setCardNo(generateMaskedCard(latestCard.getCardNo()));
        return  cardResponseDTO;

    }
    public String generateMaskedCard(String cardNo) {
        int cardLength = cardNo.length();
        String maskedCard = "";
        for (int i = 0; i < cardLength - 4; i++) {
            maskedCard += 'X';
        }
        maskedCard += cardNo.substring(cardLength - 4);
        return maskedCard;
    }
}
