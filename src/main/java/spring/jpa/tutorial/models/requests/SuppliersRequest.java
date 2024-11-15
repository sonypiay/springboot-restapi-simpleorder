package spring.jpa.tutorial.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuppliersRequest {

    @NotBlank(message = "nama wajib diisi")
    private String name;

    @NotBlank(message = "kode wajib diisi")
    private String code;

    @NotBlank(message = "email wajib diisi")
    private String email;

    private String phone_number;

    private boolean publish;
}
