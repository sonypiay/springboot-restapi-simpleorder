package spring.jpa.tutorial.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuppliersAddressResponse {

    private Integer id;

    private String name;

    private String province;

    private String city;

    private String address;

    private Integer postal_code;

    private boolean main = false;
}
