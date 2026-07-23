package app.dto;

public class InstrumentResponse {
    private String seccode;
    private String type;
    private Integer lotSize;
    private Double minStep;
    
    public InstrumentResponse() {}
    
    public InstrumentResponse(String seccode, String type, Integer lotSize, Double minStep) {
        this.seccode = seccode;
        this.type = type;
        this.lotSize = lotSize;
        this.minStep = minStep;
    }
    
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
}