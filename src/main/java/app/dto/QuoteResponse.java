package app.dto;

public class QuoteResponse {
    private String seccode;
    private Double bid;   // лучшая цена покупки
    private Double ask;   // лучшая цена продажи
    
    public QuoteResponse() {}
    
    public QuoteResponse(String seccode, Double bid, Double ask) {
        this.seccode = seccode;
        this.bid = bid;
        this.ask = ask;
    }
    
    public String getSeccode() {
        return seccode;
    }
    
    public void setSeccode(String seccode) {
        this.seccode = seccode;
    }
    
    public Double getBid() {
        return bid;
    }
    
    public void setBid(Double bid) {
        this.bid = bid;
    }
    
    public Double getAsk() {
        return ask;
    }
    
    public void setAsk(Double ask) {
        this.ask = ask;
    }
    
    
    @Override
    public String toString() {
        return "QuoteResponse{" +
                "seccode='" + seccode + '\'' +
                ", bid=" + bid +
                ", ask=" + ask +
                '}';
    }
}