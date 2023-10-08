package com.glauber.bankslip.model.exception;

public class BankSlipRecordNotFoundException extends RuntimeException{
    public BankSlipRecordNotFoundException() {
        super("Informação de boleto não encontrada!");
    }
}
