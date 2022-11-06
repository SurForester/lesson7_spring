package ru.gb.lesson7.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.gb.lesson7.Converters.ProductsConverter;
import ru.gb.lesson7.Entities.Products.ProductDto;
import ru.gb.lesson7.Services.ProductsService;
import ru.gb.lesson7.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductsService productsService;
    private final ProductsConverter productsConverter;

    @GetMapping
    public Page<ProductDto> getProducts(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "min_cost", required = false) Integer min_cost,
            @RequestParam(name = "max_cost", required = false) Integer max_cost,
            @RequestParam(name = "title_part", required = false) String title_part,
            @RequestParam(name = "sort_col", required = false) String sort_col
    ) {
        if (page < 1) {
            page = 1;
        }
        return productsService.find(page, min_cost, max_cost, title_part, sort_col)
                .map(p -> productsConverter.ProductToDto(p));
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productsConverter.ProductToDto(productsService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id)));
    }

    @PostMapping
    public ProductDto saveNewProduct(@RequestBody ProductDto productDto) {
        productDto.setId(null);
        return productsConverter.ProductToDto(productsService
                .save(productsConverter.DtoToProduct(productDto)));
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productsConverter.ProductToDto(productsService
                .save(productsConverter.DtoToProduct(productDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productsService.deleteById(id);
    }

}
