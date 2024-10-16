package com.jhops10.agregador_investimentos.repository;

import com.jhops10.agregador_investimentos.entity.Account;
import com.jhops10.agregador_investimentos.entity.AccountStock;
import com.jhops10.agregador_investimentos.entity.AccountStockId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {
}
