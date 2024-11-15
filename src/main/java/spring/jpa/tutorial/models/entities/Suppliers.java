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
@Entity(name = "suppliers")
@Table(
        name = "suppliers",
        uniqueConstraints = {
                @UniqueConstraint(name = "supplier_email_unique", columnNames = "email"),
                @UniqueConstraint(name = "supplier_code_unique", columnNames = "code")
        }
)
public class Suppliers {

    @Id
    private Integer id;

    private String name;

    private String code;

    private String email;

    private String phone_number;

    private boolean publish = true;

    @Column(name = "created_at", columnDefinition = "datetime")
    private Date createdAt = new Date();

    @Column(name = "updated_at", columnDefinition = "datetime")
    private Date updatedAt = new Date();

    @ManyToMany(mappedBy = "suppliers")
    private List<Products> products;

    @OneToMany(mappedBy = "suppliers")
    private List<SuppliersAddress> suppliersAddresses;
}
