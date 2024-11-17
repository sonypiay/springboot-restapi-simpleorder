package spring.jpa.tutorial.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.jpa.tutorial.models.entities.Categories;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer>, JpaSpecificationExecutor<Categories> {

    @Query("select exists(select 1 from categories as c where c.name = :name)")
    Boolean existsByName(@Param("name") String name);

    @Query("select exists(select 1 from categories as c where c.name = ?1 AND c.id != ?2)")
    Boolean existsByNameWithid(String name, Integer id);
}
