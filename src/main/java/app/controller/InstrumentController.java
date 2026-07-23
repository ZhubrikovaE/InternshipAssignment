package app.controller;

import app.dto.InstrumentRequest;
import app.dto.InstrumentResponse;
import app.entity.Instrument;
import app.repository.InstrumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/instruments")
public class InstrumentController {

    @Autowired
    private InstrumentRepository instrumentRepository;

    // Создание инструмента
    @PostMapping
    public ResponseEntity<InstrumentResponse> createInstrument(@RequestBody InstrumentRequest request) {
        // Создаём сущность из DTO
        Instrument instrument = new Instrument(
            request.getSeccode(),
            request.getType(),
            request.getLotSize(),
            request.getMinStep()
        );

        // Сохраняем в БД
        Instrument saved = instrumentRepository.save(instrument);

        // Преобразуем в DTO для ответа
        InstrumentResponse response = new InstrumentResponse(
            saved.getSeccode(),
            saved.getType(),
            saved.getLotSize(),
            saved.getMinStep()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Получение инструмента по seccode
    @GetMapping("/{seccode}")
    public ResponseEntity<?> getInstrument(@PathVariable String seccode) {
        Instrument instrument = instrumentRepository.findBySeccode(seccode).orElse(null);
        
        if (instrument == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Инструмент с кодом '" + seccode + "' не найден");
        }

        InstrumentResponse response = new InstrumentResponse(
            instrument.getSeccode(),
            instrument.getType(),
            instrument.getLotSize(),
            instrument.getMinStep()
        );

        return ResponseEntity.ok(response);
    }
}