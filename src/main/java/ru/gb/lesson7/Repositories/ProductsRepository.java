package ru.gb.lesson7.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.lesson7.Entities.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCostBetween(Float min, Float max);

    List<Product> findAllByCostBefore(Float min);

    List<Product> findAllByCostAfter(Float max);

}
