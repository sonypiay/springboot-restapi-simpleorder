package spring.jpa.tutorial.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesResponse {
    private Integer id;

    private String name;

    private String description;

    private boolean publish = true;

    @Transient
    private Date createdAt;

    @Transient
    private Date updatedAt;

    public String getCreatedAt() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(createdAt);
    }

    public String getUpdatedAt() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(updatedAt);
    }
}
