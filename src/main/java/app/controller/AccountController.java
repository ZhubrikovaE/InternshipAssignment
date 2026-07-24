package app.controller;

import app.dto.AccountRequest;
import app.dto.AccountResponse;
import app.entity.Account;
import app.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер счетов
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    // Создание счета
    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest request) {
        Account account = new Account();
        account.setUserId(request.getUserId());
        account.setCurrency(request.getCurrency());
        
        if (request.getBalance() != null) {
            account.setBalance(request.getBalance());
        } else {
            account.setBalance(0.0);
        }

        Account saved = accountRepository.save(account);

        AccountResponse response = new AccountResponse(
            saved.getId(),
            saved.getUserId(),
            saved.getBalance(),
            saved.getCurrency()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Получение счета по ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getAccount(@PathVariable Long id) {
        Account account = accountRepository.findById(id).orElse(null);
        
        if (account == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Счет с ID " + id + " не найден");
        }

        AccountResponse response = new AccountResponse(
            account.getId(),
            account.getUserId(),
            account.getBalance(),
            account.getCurrency()
        );

        return ResponseEntity.ok(response);
    }
}