package com.example.shoppingverse.Exception;

public class InvalidCardException extends RuntimeException{

    public InvalidCardException(String message){
        super(message);
    }
}
