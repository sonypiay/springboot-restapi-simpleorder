package spring.jpa.tutorial.utils;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.ObjectError;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WebErrorResponse {
    private List<String> errors;
    private String message;
}
