package com.example.wealthFund.repository;

import com.example.wealthFund.repository.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WalletRepository extends JpaRepository<Wallet,Long> {

    @Query("SELECT CASE WHEN COUNT(w) > 0 THEN true ELSE false END FROM Wallet w WHERE w.name = :name AND w.user.name = :userName")
    boolean existsByWalletNameAndUserName(@Param("name") String name, @Param("userName") String userName);

}
