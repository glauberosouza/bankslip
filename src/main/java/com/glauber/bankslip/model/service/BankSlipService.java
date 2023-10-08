package com.glauber.bankslip.model.service;

import com.glauber.bankslip.model.BankSlipDocument;
import com.glauber.bankslip.model.entity.BankSlip;
import com.glauber.bankslip.model.entity.BankSlipStatus;
import com.glauber.bankslip.model.exception.BankSlipRecordNotFoundException;
import com.glauber.bankslip.model.repository.BankSlipRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Slf4j
@Service
public class BankSlipService {

    @Autowired
    private BankSlipRepository repository;

    public BankSlip findById(long id) {
        return repository.findById(id)
                .orElseThrow(BankSlipRecordNotFoundException::new);
    }

    public BankSlipDocument generate(BankSlip bankSlip) {
        return bankSlip.toBankSlipDocument();
    }

    @Transactional
    public void update(BankSlip bankSlip) {
        bankSlip.setSentAt(LocalDate.now());
        bankSlip.setStatus(BankSlipStatus.SENT_TO_USER);
        repository.save(bankSlip);
        log.info("Atualizado status do boleto id: [{}]", bankSlip.getId());
    }
}