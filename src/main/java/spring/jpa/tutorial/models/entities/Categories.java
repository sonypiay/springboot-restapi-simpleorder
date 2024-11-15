package spring.jpa.tutorial.models.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "categories")
@Table(name="categories")
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    private boolean publish = true;

    @Column(name = "created_at", columnDefinition = "datetime")
    private Date createdAt = new Date();

    @Column(name = "updated_at", columnDefinition = "datetime")
    private Date updatedAt = new Date();

    @OneToMany(mappedBy = "categories")
    private List<Products> products;
}
