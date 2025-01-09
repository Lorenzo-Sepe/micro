package it.cgmconsulting.tag.exception;

import lombok.Getter;

@Getter
public class UnauthorizedException extends RuntimeException{

    private final String messageError;

    public UnauthorizedException(String messageError) {
        super(String.format(messageError));
        this.messageError = messageError;
    }
}
