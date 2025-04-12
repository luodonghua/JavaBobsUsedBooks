package com.bobsusedbooks.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookFilterDto {
    private Integer genreId;
    private Integer publisherId;
    private Integer bookTypeId;
    private Integer conditionId;
    private Boolean inStock;
}
