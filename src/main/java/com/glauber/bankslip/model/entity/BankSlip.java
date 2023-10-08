package com.glauber.bankslip.model.entity;

import com.glauber.avro.bank_slip_record.BankSlipRecord;
import com.glauber.bankslip.model.BankSlipDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BankSlip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    String email;
    String cpf;
    BigDecimal value;
    @Enumerated(EnumType.STRING)
    BankSlipStatus status;
    @Column(name = "created_at")
    LocalDate createdAt;
    @Column(name = "sent_at")
    LocalDate sentAt;

    public BankSlipDocument toBankSlipDocument() {
        return BankSlipDocument
                .builder()
                .name(this.name)
                .cpf(this.cpf)
                .value(this.value)
                .dueAt(LocalDate.now().plusDays(3))
                .barCode(UUID.randomUUID().toString())
                .build();
    }
}