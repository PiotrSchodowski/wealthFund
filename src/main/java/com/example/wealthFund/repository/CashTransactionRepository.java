package com.example.wealthFund.repository;

import com.example.wealthFund.repository.entity.UserCashTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashTransactionRepository extends JpaRepository<UserCashTransactionEntity,Long> {
}
