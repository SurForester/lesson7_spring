package ru.gb.lesson7.Controllers;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.gb.lesson7.Entities.Product;
import ru.gb.lesson7.Services.ProductsService;
import ru.gb.lesson7.exceptions.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public Page<Product> getProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer p,
            @RequestParam(name = "min_cost", required = false) Integer min_cost,
            @RequestParam(name = "max_cost", required = false) Integer max_cost,
            @RequestParam(name = "title_part", required = false) String title_part,
            @RequestParam(name = "sort_col", required = false) String sort_col
    ) {
        if (p < 1) {
            p = 1;
        }
        return productsService.find(p, min_cost, max_cost, title_part, sort_col);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productsService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
    }

    @PostMapping
    public Product saveNewProduct(@RequestBody Product product) {
        product.setId(null);
        return productsService.save(product);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productsService.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productsService.deleteById(id);
    }

}
