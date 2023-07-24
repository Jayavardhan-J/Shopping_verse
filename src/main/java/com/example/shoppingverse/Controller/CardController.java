package com.example.shoppingverse.Controller;

import com.example.shoppingverse.DTO.RequestDTO.CardRequestDTO;
import com.example.shoppingverse.DTO.ResponseDTO.CardResponseDTO;
import com.example.shoppingverse.Exception.CustomerNotFoundException;
import com.example.shoppingverse.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;
    @PostMapping("/add_card")
    public ResponseEntity addCard(@RequestBody CardRequestDTO cardRequestDTO){
        try{
            CardResponseDTO cardResponseDTO = cardService.addCard(cardRequestDTO);
            return new ResponseEntity(cardResponseDTO, HttpStatus.CREATED);
        }
        catch (CustomerNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
