package app.controller;

import app.dto.OrderRequest;
import app.dto.OrderResponse;
import app.entity.Account;
import app.entity.Instrument;
import app.entity.Order;
import app.entity.Trade;
import app.repository.AccountRepository;
import app.repository.InstrumentRepository;
import app.repository.OrderRepository;
import app.repository.TradeRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InstrumentRepository instrumentRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TradeRepository tradeRepository;

    // Создание заявки
   @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request) {
        try {
            // 1. Проверяем инструмент
            Instrument instrument = instrumentRepository.findBySeccode(request.getSeccode())
                    .orElse(null);
            if (instrument == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Инструмент с кодом '" + request.getSeccode() + "' не найден");
            }

            // 2. Проверяем счет
            Account account = accountRepository.findById(request.getAccountId())
                    .orElse(null);
            if (account == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Счет с ID " + request.getAccountId() + " не найден");
            }

            // 3. Проверяем тип заявки
            if (!"BUY".equalsIgnoreCase(request.getType()) && !"SELL".equalsIgnoreCase(request.getType())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Тип заявки должен быть 'BUY' или 'SELL'");
            }

            // 4. Проверяем цену
            if (request.getPrice() == null || request.getPrice() <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Цена должна быть положительным числом");
            }

            // 5. Проверяем количество
            if (request.getQuantity() == null || request.getQuantity() <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Количество должно быть положительным числом");
            }

            // 6. Проверяем, что количество кратно лоту
            if (request.getQuantity() % instrument.getLotSize() != 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Количество должно быть кратно размеру лота (" + instrument.getLotSize() + ")");
            }

            // 7. Проверяем, что цена соответствует минимальному шагу
            double remainder = Math.round(request.getPrice() % instrument.getMinStep());
            if (remainder > 0.01) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Цена должна быть кратна минимальному шагу (" + instrument.getMinStep() + ")");
            }

            // 8. Для заявок на покупку: проверяем баланс и замораживаем средства
            if ("BUY".equalsIgnoreCase(request.getType())) {
                double totalCost = request.getPrice() * request.getQuantity();
                if (account.getBalance() < totalCost) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("Недостаточно средств. Необходимо: " + totalCost + 
                                  ", доступно: " + account.getBalance());
                }
                account.setBalance(account.getBalance() - totalCost);
                accountRepository.save(account);
            }

            // 9. Создаем заявку со статусом PENDING
            Order order = new Order(
                account,
                request.getSeccode(),
                request.getType().toUpperCase(),
                request.getPrice(),
                request.getQuantity()
            );

            Order savedOrder = orderRepository.save(order);

            // 10. СОПОСТАВЛЕНИЕ ЗАЯВОК
            List<Trade> createdTrades = matchOrder(savedOrder);

            // 11. Формируем ответ
            OrderResponse response = new OrderResponse(
                savedOrder.getId(),
                savedOrder.getSeccode(),
                savedOrder.getAccount().getId(),
                savedOrder.getType(),
                savedOrder.getPrice(),
                savedOrder.getQuantity(),
                savedOrder.getStatus(),
                savedOrder.getCreatedAt()
            );

            // Добавляем информацию о созданных сделках
            if (!createdTrades.isEmpty()) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body("Заявка создана. Создано сделок: " + createdTrades.size() + 
                              ". Заявка: " + response);
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ошибка при создании заявки: " + e.getMessage());
        }
    }

    /**
     * Сопоставление заявки с встречными заявками
     */
    private List<Trade> matchOrder(Order newOrder) {
        List<Trade> createdTrades = new ArrayList<>();
        
        // Определяем встречный тип
        String counterType = "BUY".equals(newOrder.getType()) ? "SELL" : "BUY";
        
        // Ищем все PENDING заявки встречного типа по тому же инструменту
        List<Order> counterOrders = orderRepository.findBySeccodeAndTypeAndStatus(
            newOrder.getSeccode(),
            counterType,
            "PENDING"
        );
        
        for (Order counterOrder : counterOrders) {
            // Не сопоставляем с самим собой
            if (counterOrder.getId().equals(newOrder.getId())) {
                continue;
            }
            
            // Проверяем равенство цен
            if (!newOrder.getPrice().equals(counterOrder.getPrice())) {
                continue;
            }
            
            // Определяем количество для сделки
            int tradeQuantity = Math.min(
                newOrder.getQuantity(),
                counterOrder.getQuantity()
            );
            
            // Определяем, какая заявка BUY, какая SELL
            Order buyOrder = "BUY".equals(newOrder.getType()) ? newOrder : counterOrder;
            Order sellOrder = "SELL".equals(newOrder.getType()) ? newOrder : counterOrder;
            
            // Получаем инструмент
            Instrument instrument = instrumentRepository.findBySeccode(newOrder.getSeccode())
                    .orElse(null);
            if (instrument == null) continue;
            
            // Создаем сделку
            Trade trade = new Trade(
                instrument,
                buyOrder,
                sellOrder,
                newOrder.getPrice(),
                tradeQuantity
            );
            
            tradeRepository.save(trade);
            createdTrades.add(trade);
            
            // Обновляем заявки
            newOrder.setQuantity(newOrder.getQuantity() - tradeQuantity);
            counterOrder.setQuantity(counterOrder.getQuantity() - tradeQuantity);
            
            if (newOrder.getQuantity() == 0) {
                newOrder.setStatus("EXECUTED");
            } else {
                newOrder.setStatus("PENDING");
            }
            
            if (counterOrder.getQuantity() == 0) {
                counterOrder.setStatus("EXECUTED");
            } else {
                counterOrder.setStatus("PENDING");
            }
            
            orderRepository.save(newOrder);
            orderRepository.save(counterOrder);
            
            // Если новая заявка полностью исполнена, выходим
            if (newOrder.getQuantity() == 0) {
                break;
            }
        }
        
        return createdTrades;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        
        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Заявка с ID " + id + " не найдена");
        }

        OrderResponse response = new OrderResponse(
            order.getId(),
            order.getSeccode(),
            order.getAccount().getId(),
            order.getType(),
            order.getPrice(),
            order.getQuantity(),
            order.getStatus(),
            order.getCreatedAt()
        );

        return ResponseEntity.ok(response);
    }
}