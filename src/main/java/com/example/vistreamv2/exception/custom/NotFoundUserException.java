package com.example.vistreamv2.exception.custom;

public class NotFoundUserException extends RuntimeException{
    public NotFoundUserException(String content){
        super(content);
    }

}
