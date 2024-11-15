package spring.jpa.tutorial.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesRequest {

    @NotBlank(message = "nama wajib diisi")
    @Size(max = 100)
    private String name;

    private String description;

    private boolean publish;
}
