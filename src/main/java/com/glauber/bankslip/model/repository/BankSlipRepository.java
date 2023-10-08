package com.glauber.bankslip.model.repository;

import com.glauber.bankslip.model.entity.BankSlip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankSlipRepository extends JpaRepository<BankSlip, Long> {
}
