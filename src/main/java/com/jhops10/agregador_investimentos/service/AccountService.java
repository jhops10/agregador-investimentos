package com.jhops10.agregador_investimentos.service;

import com.jhops10.agregador_investimentos.client.BrapiClient;
import com.jhops10.agregador_investimentos.controller.dto.AccountStockDto;
import com.jhops10.agregador_investimentos.controller.dto.AccountStockResponseDto;
import com.jhops10.agregador_investimentos.entity.AccountStock;
import com.jhops10.agregador_investimentos.entity.AccountStockId;
import com.jhops10.agregador_investimentos.repository.AccountRepository;
import com.jhops10.agregador_investimentos.repository.AccountStockRepository;
import com.jhops10.agregador_investimentos.repository.StockRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    @Value("#{environment.TOKEN}")
    private String TOKEN;

    private final StockRepository stockRepository;
    private final AccountRepository accountRepository;
    private final AccountStockRepository accountStockRepository;
    private final BrapiClient brapiClient;

    public AccountService(StockRepository stockRepository,
                          AccountRepository accountRepository,
                          AccountStockRepository accountStockRepository, BrapiClient brapiClient) {
        this.stockRepository = stockRepository;
        this.accountRepository = accountRepository;
        this.accountStockRepository = accountStockRepository;
        this.brapiClient = brapiClient;
    }


    public void associateStock(String accountId, AccountStockDto accountStockDto) {

        var account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account nao existe"));

        var stock = stockRepository.findById(accountStockDto.stockId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock nao existe"));

        var id = new AccountStockId(account.getAccountId(), stock.getStockId());

        var accountStockEntity = new AccountStock(id, account, stock, accountStockDto.quantity());

        accountStockRepository.save(accountStockEntity);
    }

    public List<AccountStockResponseDto> listStocks(String accountId) {

        var account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account nao existe"));

        return account.getAccountStocks()
                .stream()
                .map(ac -> new AccountStockResponseDto(
                        ac.getStock().getStockId(),
                        ac.getQuantity(),
                        getTotal(ac.getQuantity(), ac.getStock().getStockId())
                ))
                .toList();

    }

    private double getTotal(int quantity, String stockId) {

        var response = brapiClient.getQuote(TOKEN, stockId);

        var price = response.results().get(0).regularMarketPrice();

        return quantity * price;
    }
}
