package spring.jpa.tutorial.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductsRequest {

    @NotBlank(message = "nama wajib diisi")
    private String name;

    @NotBlank(message = "sku wajib diisi")
    private String sku;

    private String description;

    @NotBlank(message = "harga wajib diisi")
    private Integer price = 0;

    private Integer stock = 0;

    private boolean publish = true;

    @NotBlank(message = "kategori wajib diisi")
    private Integer categories_id;
}
