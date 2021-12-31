package com.kaibacorp.testetqi.domain.exception;

public class DontFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public DontFoundException(String msg){
        super(msg);
    }
}
