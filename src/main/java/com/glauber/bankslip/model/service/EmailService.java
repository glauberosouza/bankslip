package com.glauber.bankslip.model.service;

import com.glauber.bankslip.model.BankSlipDocument;
import com.glauber.bankslip.model.exception.EmailSendException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    public void sendMessage(String email, BankSlipDocument bankSlip) {

        try {
            log.info("Try to send email for : " + email);

            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom("noreply@boletofacil.com");
            message.setTo(email);
            message.setSubject("Seu boleto chegou :)");
            message.setText(
                    String.format("Olá %s, seguem os dados do seu boleto " +
                                    "\n CPF: %s " +
                                    "\n VALOR: %s " +
                                    "\n VENCIMENTO: %s " +
                                    "\n CÓDIGO DE BARRAS: %s",
                            bankSlip.getName(),
                            bankSlip.getCpf(),
                            bankSlip.getValue().toString(),
                            bankSlip.getDueAt().toString(),
                            bankSlip.getBarCode())
            );

            emailSender.send(message);

        } catch (MailException ex) {
            throw new EmailSendException();
        }
    }
}