package ru.gb.lesson7.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.gb.lesson7.Entities.Products.Product;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long>,
        JpaSpecificationExecutor<Product> {
}
