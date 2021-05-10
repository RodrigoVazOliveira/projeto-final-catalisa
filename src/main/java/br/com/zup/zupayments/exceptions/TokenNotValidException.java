package br.com.zup.zupayments.exceptions;

public class TokenNotValidException extends RuntimeException{

    public TokenNotValidException(String message) {
        super(message);
    }
}