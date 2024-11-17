package spring.jpa.tutorial.models.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchCategoriesRequest {
    private String name;

    private String description;

    private boolean publish = true;
}
