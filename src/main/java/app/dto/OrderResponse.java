package app.dto;

import java.time.LocalDateTime;

public class OrderResponse {
    private Long id;
    private String seccode;
    private Long accountId;
    private String type;
    private Double price;
    private Integer quantity;
    private String status;
    private LocalDateTime createdAt;
    
    public OrderResponse() {}
    
    public OrderResponse(Long id, String seccode, Long accountId, String type, Double price, Integer quantity, String status, LocalDateTime createdAt) {
        this.id = id;
        this.seccode = seccode;
        this.accountId = accountId;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.createdAt = createdAt;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getSeccode() {
        return seccode;
    }
    
    public void setSeccode(String seccode) {
        this.seccode = seccode;
    }
    
    public Long getAccountId() {
        return accountId;
    }
    
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}