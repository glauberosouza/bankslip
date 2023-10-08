package com.glauber.bankslip.model.exception;

public class EmailSendException extends RuntimeException {
    public EmailSendException() {
        super("Não foi possível enviar o email.");
    }
}
