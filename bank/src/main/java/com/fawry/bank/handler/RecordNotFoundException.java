package com.fawry.bank.handler;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(){
        super();
    }
    public RecordNotFoundException(String message) {
        super(message);
    }


}
