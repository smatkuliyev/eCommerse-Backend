package com.edu.ecom.exceptions;

public class AuthenticationFailException  extends IllegalArgumentException{
    public AuthenticationFailException(String msg){
        super(msg);
    }
}
