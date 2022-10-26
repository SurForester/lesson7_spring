package ru.gb.lesson7.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.lesson7.Entities.Product;
import ru.gb.lesson7.Repositories.ProductsRepository;
import ru.gb.lesson7.Services.ProductsService;
import ru.gb.lesson7.exceptions.ResourceNotFoundException;

import java.util.List;

@RestController
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productsService.findAll();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productsService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
    }

    @GetMapping("/products/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        productsService.deleteById(id);
    }

    @GetMapping("/products/cost_between")
    public List<Product> findProductByCostBetween(@RequestParam(defaultValue = "0") Float min,
                                                  @RequestParam(defaultValue = "1000000") Float max) {
        return productsService.findAllByCostBetween(min, max);
    }

    @GetMapping("/products/cost_before")
    public List<Product> findProductByCostBefore(@RequestParam(defaultValue = "100") Float min) {
        return productsService.findAllByCostBeforeMin(min);
    }

    @GetMapping("/products/cost_after")
    public List<Product> findProductCostAfter(@RequestParam(defaultValue = "1000") Float max) {
        return productsService.findAllByCostAfterMax(max);
    }

}
