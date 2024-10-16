package com.jhops10.agregador_investimentos.controller;

import com.jhops10.agregador_investimentos.controller.dto.AssociateAccountStockDto;
import com.jhops10.agregador_investimentos.controller.dto.CreateAccountDto;
import com.jhops10.agregador_investimentos.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/{accountId}/stocks")
    public ResponseEntity<Void> associateStock(@PathVariable("accountId") String accountId,
                                              @RequestBody AssociateAccountStockDto associateAccountStockDto) {
        accountService.associateStock(accountId, associateAccountStockDto);
        return ResponseEntity.ok().build();
    }

}
