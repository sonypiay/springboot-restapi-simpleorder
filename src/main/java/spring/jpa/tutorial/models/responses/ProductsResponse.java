package spring.jpa.tutorial.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductsResponse {

    private Integer id;

    private String name;

    private String sku;

    private String description;

    private Integer price = 0;

    private Integer stock = 0;

    private boolean publish = true;
}
