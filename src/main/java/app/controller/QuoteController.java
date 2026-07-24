package app.controller;

import app.dto.QuoteResponse;
import app.entity.Instrument;
import app.repository.InstrumentRepository;
import app.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private InstrumentRepository instrumentRepository;

    /**
     * Получение котировки по конкретному инструменту
     */
    @GetMapping("/{seccode}")
    public ResponseEntity<?> getQuoteBySeccode(@PathVariable String seccode) {
        // Проверяем, существует ли инструмент
        Instrument instrument = instrumentRepository.findBySeccode(seccode).orElse(null);
        if (instrument == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Инструмент с кодом '" + seccode + "' не найден");
        }

        // Получаем лучшие цены
        Double bid = orderRepository.findMaxBidPriceBySeccode(seccode);
        Double ask = orderRepository.findMinAskPriceBySeccode(seccode);
    

        QuoteResponse response = new QuoteResponse(
            seccode,
            bid,
            ask
        );

        return ResponseEntity.ok(response);
    }

    /**
     * Получение котировок по всем инструментам
     */
    @GetMapping
    public ResponseEntity<List<QuoteResponse>> getAllQuotes() {
        List<Instrument> instruments = instrumentRepository.findAll();
        List<QuoteResponse> quotes = new ArrayList<>();

        for (Instrument instrument : instruments) {
            String seccode = instrument.getSeccode();
            
            Double bid = orderRepository.findMaxBidPriceBySeccode(seccode);
            Double ask = orderRepository.findMinAskPriceBySeccode(seccode);
        
            
            QuoteResponse response = new QuoteResponse(
                seccode,
                bid,
                ask
            );
            
            quotes.add(response);
        }

        return ResponseEntity.ok(quotes);
    }
}