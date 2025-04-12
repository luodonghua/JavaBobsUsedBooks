package com.bobsusedbooks.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderFilterDto {
    private Integer customerId;
    private Integer orderStatus;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
