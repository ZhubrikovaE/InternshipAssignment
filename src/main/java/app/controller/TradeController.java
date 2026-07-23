package app.controller;

import app.dto.TradeResponse;
import app.entity.Trade;
import app.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/trades")
public class TradeController {

    @Autowired
    private TradeRepository tradeRepository;
   
    /**
     * Получение всех сделок
     */
    @GetMapping
    public ResponseEntity<List<TradeResponse>> getAllTrades() {
        List<Trade> trades = tradeRepository.findAll();

        List<TradeResponse> responses = new ArrayList<>();
        for (Trade trade : trades) {
            TradeResponse response = new TradeResponse(
                    trade.getId(),
                    trade.getInstrument().getSeccode(),
                    trade.getBuyOrder().getId(),
                    trade.getSellOrder().getId(),
                    trade.getPrice(),
                    trade.getQuantity(),
                    trade.getExecutedAt());
            responses.add(response);
        }

        return ResponseEntity.ok(responses);
    }
}