package spring.jpa.tutorial.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.jpa.tutorial.models.entities.Categories;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer> {

    @Query("select exists(select 1 from categories as c where c.name = :name)")
    Boolean existsByName(@Param("name") String name);
}
