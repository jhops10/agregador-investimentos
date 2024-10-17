package com.jhops10.agregador_investimentos.controller;

import com.jhops10.agregador_investimentos.controller.dto.CreateStockDto;
import com.jhops10.agregador_investimentos.entity.User;
import com.jhops10.agregador_investimentos.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/v1/stocks")
public class StockController {

    private StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity<User> createStock(@RequestBody CreateStockDto createStockDto) {
        stockService.createStock(createStockDto);

        return ResponseEntity.ok().build();
    }
}
