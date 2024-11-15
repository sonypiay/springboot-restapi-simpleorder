package spring.jpa.tutorial.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.jpa.tutorial.models.entities.Products;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer> {
    List<Products> findAllByCode(String code);
}
