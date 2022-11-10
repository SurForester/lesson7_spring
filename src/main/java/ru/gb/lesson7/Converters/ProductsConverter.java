package ru.gb.lesson7.Converters;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;
import ru.gb.lesson7.Entities.Products.Product;
import ru.gb.lesson7.Entities.Products.ProductDto;

@Component
public class ProductsConverter {

    public ProductDto ProductToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getCost());
    }

    public Product DtoToProduct(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getCost());
    }

}
