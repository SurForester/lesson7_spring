package ru.gb.lesson7.Entities.Products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInCartDto {
    private long id;
    private String title;
    private Float cost;
    private Integer quantity;
}
