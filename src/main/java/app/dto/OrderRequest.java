package app.dto;

public class OrderRequest {
    private String seccode;
    private Long accountId;
    private String type;
    private Double price;
    private Integer quantity;
    
    public OrderRequest() {}
    
    public OrderRequest(String seccode, Long accountId, String type, Double price, Integer quantity) {
        this.seccode = seccode;
        this.accountId = accountId;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
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
}