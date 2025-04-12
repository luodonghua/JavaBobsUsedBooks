package com.bobsusedbooks.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportItemDto {
    private Long id;
    private String name;
    private String author;
    private String category;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal totalValue;
    
    public BigDecimal getTotal() {
        return totalValue;
    }
    
    public void setTotal(BigDecimal total) {
        this.totalValue = total;
    }
}
