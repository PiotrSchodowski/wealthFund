package com.example.wealthFund.repository;

import com.example.wealthFund.repository.entity.UserCashTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashTransactionRepository extends JpaRepository<UserCashTransaction,Long> {
}
