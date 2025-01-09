package it.cgmconsulting.tag.exception;

import lombok.Getter;

@Getter
public class InternalServerErrorException extends RuntimeException{

    private final String messageError;

    public InternalServerErrorException(String messageError) {
        super(String.format(messageError));
        this.messageError = messageError;
    }
}
