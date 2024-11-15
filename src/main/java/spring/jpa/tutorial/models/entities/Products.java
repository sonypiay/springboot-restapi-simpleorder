package spring.jpa.tutorial.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Products")
@Table(
        name = "products",
        uniqueConstraints = {
                @UniqueConstraint(name = "products_code_unique", columnNames = "code")
        },
        indexes = {
                @Index(name = "products_categories_id_foreign", columnList = "categories_id")
        }
)
public class Products {

    @Id
    private Integer id;

    private String name;

    private String code;

    private String description;

    private Integer price = 0;

    private Integer stock = 0;

    private boolean publish = true;

    @Column(name = "created_at", nullable = false, columnDefinition = "datetime")
    private Date createdAt = new Date();

    @Column(name = "updated_at", nullable = false, columnDefinition = "datetime")
    private Date updatedAt = new Date();

    @ManyToOne
    @JoinColumn(
            name = "categories_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "products_categories_id_foreign")
    )
    private Categories categories;

    @ManyToMany
    @JoinTable(
            name = "products_suppliers",
            joinColumns = @JoinColumn(name = "supplier_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "ps_supplier_id_foreign")),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "ps_product_id_foreign")),
            indexes = {
                    @Index(name = "ps_supplier_id_foreign", columnList = "supplier_id"),
                    @Index(name = "ps_product_id_foreign", columnList = "product_id")
            }
    )
    private List<Suppliers> suppliers;
}
