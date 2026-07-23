package app.dto;

public class AccountRequest {
    private Long userId;
    private String currency;
    private Double balance;
    
    public AccountRequest() {}
    
    public AccountRequest(Long userId, String currency, Double balance) {
        this.userId = userId;
        this.currency = currency;
        this.balance = balance;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getCurrency() {
        return currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public Double getBalance() {
        return balance;
    }
    
    public void setBalance(Double balance) {
        this.balance = balance;
    }
}