package com.jhops10.agregador_investimentos.repository;

import com.jhops10.agregador_investimentos.entity.Account;
import com.jhops10.agregador_investimentos.entity.BillingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BillingAddressRepository extends JpaRepository<BillingAddress, UUID> {
}
