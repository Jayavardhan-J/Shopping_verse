package com.example.shoppingverse.Transformer;

import com.example.shoppingverse.DTO.RequestDTO.CardRequestDTO;
import com.example.shoppingverse.DTO.ResponseDTO.CardResponseDTO;
import com.example.shoppingverse.Model.Card;

public class CardTransformer {
    public static Card cardRequestDtoToCard(CardRequestDTO cardRequestDTO){
        return Card.builder().cardNo(cardRequestDTO.getCardNo())
                .cardType(cardRequestDTO.getCardType())
                .validTill(cardRequestDTO.getValidTill())
                .cvv(cardRequestDTO.getCvv())
                .build();
    }
    public static CardResponseDTO cardToCardResponseDTO(Card card){
        return CardResponseDTO.builder().customerName(card.getCustomer().getName())
                .validTill(card.getValidTill())
                .cardType(card.getCardType())
                .build();
    }
}
