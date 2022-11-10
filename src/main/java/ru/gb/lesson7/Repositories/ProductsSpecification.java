package ru.gb.lesson7.Repositories;

import org.springframework.data.jpa.domain.Specification;
import ru.gb.lesson7.Entities.Products.Product;

public class ProductsSpecification {

    public static Specification<Product> costGreaterOrEqualsTo(Integer min_cost) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), min_cost);
    }

    public static Specification<Product> costLessThanOrEqualTo(Integer max_cost) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("cost"), max_cost);
    }

    public static Specification<Product> titleLike(String title_part) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("title"), String.format("%%%s%%", title_part));
    }

}
