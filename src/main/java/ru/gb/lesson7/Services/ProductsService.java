package ru.gb.lesson7.Services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.lesson7.Entities.Products.Product;
import ru.gb.lesson7.Entities.Products.ProductDto;
import ru.gb.lesson7.Repositories.ProductsRepository;
import ru.gb.lesson7.Repositories.ProductsSpecification;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductsRepository productsRepository;

    public Page<Product> find(Integer page, Integer minCost, Integer maxCost,
                              String partTitle, String sortColumn) {
        Specification<Product> spec = Specification.where(null);
        if (minCost != null) {
            spec = spec.and(ProductsSpecification.costGreaterOrEqualsTo(minCost));
        }
        if (maxCost != null) {
            spec = spec.and(ProductsSpecification.costLessThanOrEqualTo(maxCost));
        }
        if (partTitle != null) {
            spec = spec.and(ProductsSpecification.titleLike(partTitle));
        }
        if (sortColumn == null) {
            sortColumn = "id";
        }
        Page<Product> list = productsRepository.findAll(spec,
                PageRequest.of(page - 1, 10, Sort.Direction.ASC, sortColumn));
        return list;
    }

    public Optional<Product> findById(Long id) {
        return productsRepository.findById(id);
    }

    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

    public Product save(Product product) {
        return productsRepository.saveAndFlush(product);
    }

}
