package ru.gb.lesson7.Services;

import org.springframework.stereotype.Service;
import ru.gb.lesson7.Entities.Product;
import ru.gb.lesson7.Repositories.ProductsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

    private ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Product> findAll() {
        return productsRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productsRepository.findById(id);
    }

    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

    public List<Product> findAllByCostBetween(Float min, Float max) {
        return productsRepository.findAllByCostBetween(min, max);
    }

    public List<Product> findAllByCostBeforeMin(Float min) {
        return productsRepository.findAllByCostBefore(min);
    }

    public List<Product> findAllByCostAfterMax(Float max) {
        return productsRepository.findAllByCostAfter(max);
    }

}
