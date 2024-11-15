package spring.jpa.tutorial.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.jpa.tutorial.models.entities.SuppliersAddress;

@Repository
public interface SuppliersAddressRepository extends JpaRepository<SuppliersAddress, Integer> {
}
