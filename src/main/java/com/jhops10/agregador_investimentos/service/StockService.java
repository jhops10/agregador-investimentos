package com.jhops10.agregador_investimentos.service;

import com.jhops10.agregador_investimentos.controller.dto.CreateStockDto;
import com.jhops10.agregador_investimentos.entity.Stock;
import com.jhops10.agregador_investimentos.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void createStock(CreateStockDto createStockDto) {

       //DTO -> Entity
        var stock = new Stock(
                createStockDto.stockId(),
                createStockDto.description()
        );

        stockRepository.save(stock);
    }
}
