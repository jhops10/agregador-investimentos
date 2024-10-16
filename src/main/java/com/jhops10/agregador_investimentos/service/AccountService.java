package com.jhops10.agregador_investimentos.service;

import com.jhops10.agregador_investimentos.controller.dto.AssociateAccountStockDto;
import com.jhops10.agregador_investimentos.entity.AccountStock;
import com.jhops10.agregador_investimentos.entity.AccountStockId;
import com.jhops10.agregador_investimentos.repository.AccountRepository;
import com.jhops10.agregador_investimentos.repository.AccountStockRepository;
import com.jhops10.agregador_investimentos.repository.StockRepository;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final StockRepository stockRepository;
    private final AccountStockRepository accountStockRepository;

    public AccountService(AccountRepository accountRepository, StockRepository stockRepository, AccountStockRepository accountStockRepository) {
        this.accountRepository = accountRepository;
        this.stockRepository = stockRepository;
        this.accountStockRepository = accountStockRepository;
    }



    public void associateStock(String accountId, AssociateAccountStockDto associateAccountStockDto) {

        var account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var stock = stockRepository.findById(associateAccountStockDto.stockId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        //DTO -> Entity

        var id = new AccountStockId(account.getAccount_id(), stock.getStockId());
        var entity = new AccountStock(
                id,
                account,
                stock,
                associateAccountStockDto.quantity()
        );

        accountStockRepository.save(entity);

    }
}
