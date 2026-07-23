package app.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trades")
public class Trade {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "seccode", referencedColumnName = "seccode", nullable = false)
    private Instrument instrument;
    
    @ManyToOne
    @JoinColumn(name = "buy_order_id", nullable = false)
    private Order buyOrder;
    
    @ManyToOne
    @JoinColumn(name = "sell_order_id", nullable = false)
    private Order sellOrder;
    
    @Column(name = "price", nullable = false)
    private Double price;
    
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    
    @Column(name = "executed_at", nullable = false)
    private LocalDateTime executedAt = LocalDateTime.now();
    
    // Конструкторы
    public Trade() {}
    
    public Trade(Instrument instrument, Order buyOrder, Order sellOrder, Double price, Integer quantity) {
        this.instrument = instrument;
        this.buyOrder = buyOrder;
        this.sellOrder = sellOrder;
        this.price = price;
        this.quantity = quantity;
        this.executedAt = LocalDateTime.now();
    }
    
    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Instrument getInstrument() {
        return instrument;
    }
    
    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }
    
    public Order getBuyOrder() {
        return buyOrder;
    }
    
    public void setBuyOrder(Order buyOrder) {
        this.buyOrder = buyOrder;
    }
    
    public Order getSellOrder() {
        return sellOrder;
    }
    
    public void setSellOrder(Order sellOrder) {
        this.sellOrder = sellOrder;
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
    
    @Override
    public String toString() {
        return "Trade{" +
                "id=" + id +
                ", instrument=" + (instrument != null ? instrument.getSeccode() : null) +
                ", buyOrder=" + (buyOrder != null ? buyOrder.getId() : null) +
                ", sellOrder=" + (sellOrder != null ? sellOrder.getId() : null) +
                ", price=" + price +
                ", quantity=" + quantity +
                ", executedAt=" + executedAt +
                '}';
    }
}