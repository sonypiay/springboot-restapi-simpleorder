package spring.jpa.tutorial.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.jpa.tutorial.models.entities.Suppliers;

@Repository
public interface SuppliersRepository extends JpaRepository<Suppliers, Integer> {
}
