package spring.jpa.tutorial.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuppliersResponse {

    private Integer id;

    private String name;

    private String code;

    private String email;

    private String phone_number;

    private boolean publish = true;

    private Date createdAt;

    private Date updatedAt;
}
