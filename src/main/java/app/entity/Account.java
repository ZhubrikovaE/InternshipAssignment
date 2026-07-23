package app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    @Column(name = "balance", nullable = false)
    private Double balance = 0.0;
    
    @Column(name = "currency", length = 10, nullable = false)
    private String currency;
    
    // Конструкторы
    public Account() {}
    
    public Account(Long userId, String currency) {
        this.userId = userId;
        this.currency = currency;
        this.balance = 0.0;
    }
    
    public Account(Long userId, Double balance, String currency) {
        this.userId = userId;
        this.balance = balance;
        this.currency = currency;
    }
    
    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public Double getBalance() {
        return balance;
    }
    
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    
    public String getCurrency() {
        return currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userId=" + userId +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                '}';
    }
}