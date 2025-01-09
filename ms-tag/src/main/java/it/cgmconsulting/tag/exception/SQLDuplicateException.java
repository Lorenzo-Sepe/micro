package it.cgmconsulting.tag.exception;

import lombok.Getter;

@Getter
public class SQLDuplicateException extends RuntimeException{

    private final String messageError;

    public SQLDuplicateException(String messageError) {
        super(String.format(messageError));
        this.messageError = messageError;
    }
}
