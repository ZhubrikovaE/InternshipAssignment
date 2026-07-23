package app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "instruments")
public class Instrument {
    
    @Id
    @Column(name = "seccode", length = 20, nullable = false, unique = true)
    private String seccode;
    
    @Column(name = "type", length = 50, nullable = false)
    private String type;
    
    @Column(name = "lot_size", nullable = false)
    private Integer lotSize;
    
    @Column(name = "min_step", nullable = false)
    private Double minStep;
    
    // Конструкторы
    public Instrument() {}
    
    public Instrument(String seccode, String type, Integer lotSize, Double minStep) {
        this.seccode = seccode;
        this.type = type;
        this.lotSize = lotSize;
        this.minStep = minStep;
    }
    
    // Геттеры и сеттеры
    public String getSeccode() {
        return seccode;
    }
    
    public void setSeccode(String seccode) {
        this.seccode = seccode;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Integer getLotSize() {
        return lotSize;
    }
    
    public void setLotSize(Integer lotSize) {
        this.lotSize = lotSize;
    }
    
    public Double getMinStep() {
        return minStep;
    }
    
    public void setMinStep(Double minStep) {
        this.minStep = minStep;
    }
    
    @Override
    public String toString() {
        return "Instrument{" +
                "seccode='" + seccode + '\'' +
                ", type='" + type + '\'' +
                ", lotSize=" + lotSize +
                ", minStep=" + minStep +
                '}';
    }
}