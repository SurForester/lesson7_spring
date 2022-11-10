package ru.gb.lesson7.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.lesson7.Entities.Products.Product;
import ru.gb.lesson7.Entities.Products.ProductInCartDto;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductsService productsService;
    private final HashMap<Long, Integer> productsList = new HashMap<>();

    public List<ProductInCartDto> getAll() {
        List<ProductInCartDto> list = new ArrayList<>();
        for (Map.Entry<Long, Integer> productInCart : productsList.entrySet()) {
            Optional<Product> product = productsService.findById(productInCart.getKey());
            if (product.isPresent()) {
                ProductInCartDto productInCartDto =
                        new ProductInCartDto(product.get().getId(), product.get().getTitle(),
                                product.get().getCost(), productInCart.getValue());
                list.add(productInCartDto);
            }
        }
        return list;
    }

    public ProductInCartDto getById(Long id) {
        ProductInCartDto productInCartDto = new ProductInCartDto();
        Optional<Product> product = productsService.findById(id);
        if (product.isPresent()) {
            productInCartDto.setId(product.get().getId());
            productInCartDto.setTitle(product.get().getTitle());
            productInCartDto.setCost(product.get().getCost());
            productInCartDto.setQuantity(productsList.get(id));
        }
        return productInCartDto;
    }

    public void addProductId(Long id) {
        productsList.merge(id, 1, Integer::sum);
    }

    public void deleteProduct(Long id) {
        Integer mapElement = productsList.get(id);
        if (mapElement != null && mapElement > 0) {
            productsList.put(id, mapElement - 1);
        }
    }

}
