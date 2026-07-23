package app.dto;

import java.time.LocalDateTime;

public class TradeResponse {
    private Long id;
    private String seccode;
    private Long buyOrderId;
    private Long sellOrderId;
    private Double price;
    private Integer quantity;
    private LocalDateTime executedAt;
    
    public TradeResponse() {}
    
    public TradeResponse(Long id, String seccode, Long buyOrderId, Long sellOrderId, Double price, Integer quantity, LocalDateTime executedAt) {
        this.id = id;
        this.seccode = seccode;
        this.buyOrderId = buyOrderId;
        this.sellOrderId = sellOrderId;
        this.price = price;
        this.quantity = quantity;
        this.executedAt = executedAt;
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
    
    public Long getBuyOrderId() {
        return buyOrderId;
    }
    
    public void setBuyOrderId(Long buyOrderId) {
        this.buyOrderId = buyOrderId;
    }
    
    public Long getSellOrderId() {
        return sellOrderId;
    }
    
    public void setSellOrderId(Long sellOrderId) {
        this.sellOrderId = sellOrderId;
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
    
    public LocalDateTime getExecutedAt() {
        return executedAt;
    }
    
    public void setExecutedAt(LocalDateTime executedAt) {
        this.executedAt = executedAt;
    }
}