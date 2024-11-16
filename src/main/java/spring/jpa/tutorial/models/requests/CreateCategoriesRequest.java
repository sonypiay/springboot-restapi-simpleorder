package spring.jpa.tutorial.models.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoriesRequest {
    @NotBlank(message = "nama wajib diisi")
    @NotNull(message = "name parameter must be required")
    @Size(max = 100)
    private String name;

    private String description;

    private boolean publish;

    private Integer page;

    private Integer size;
}