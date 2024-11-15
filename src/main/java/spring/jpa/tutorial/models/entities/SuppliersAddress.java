package spring.jpa.tutorial.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "suppliers_address")
@Table(name = "suppliers_address")
public class SuppliersAddress {

    @Id
    private Integer id;

    private String name;

    private String province;

    private String city;

    private String address;

    private Integer postal_code;

    private boolean main;

    @ManyToOne
    @JoinColumn(
            name = "supplier_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "supplier_address_supplier_id_foreign")
    )
    private Suppliers suppliers;
}
