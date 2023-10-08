package com.glauber.bankslip.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class BankSlipDocument {
    private String name;
    private String cpf;
    private BigDecimal value;
    private LocalDate dueAt;
    private String barCode;
}
