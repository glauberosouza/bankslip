package com.glauber.bankslip;

import com.glauber.avro.bank_slip_record.BankSlipRecord;
import com.glauber.bankslip.model.exception.BankSlipRecordNotFoundException;
import com.glauber.bankslip.model.exception.EmailSendException;
import com.glauber.bankslip.model.service.BankSlipService;
import com.glauber.bankslip.model.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NewBankSlipConsumer {
    @Autowired
    private EmailService emailService;

    @Autowired
    private BankSlipService bankSlipService;

    @KafkaListener(topics = "new-bank-slip", groupId = "new_bank_slip_factory")
    public void consume(BankSlipRecord record) {
        log.info("Pedido de boleto recebido id: [{}]", record.getId());

        try {

            var bankSlip = bankSlipService.findById(record.getId());
            emailService.sendMessage(record.getEmail(), bankSlipService.generate(bankSlip));

            bankSlipService.update(bankSlip);

        } catch (BankSlipRecordNotFoundException ex) {
            log.error("Erro ao gerar boleto", ex);
        } catch (EmailSendException ex) {
            log.error("Erro ao enviar email", ex);
        }
    }
}