package ru.gb.lesson7.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.lesson7.Entities.Products.ProductDto;
import ru.gb.lesson7.Entities.Products.ProductInCartDto;
import ru.gb.lesson7.Services.CartService;
import ru.gb.lesson7.exceptions.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public List<ProductInCartDto> getProductList() {
        return cartService.getAll();
    }

    @PostMapping
    public ProductInCartDto saveNewProduct(@RequestBody Long id) {
        cartService.addProductId(id);
        return cartService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        cartService.deleteProduct(id);
    }


}
