package spring.jpa.tutorial.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pagination {

    private Integer currentPage;

    private Integer totalPage;

    private Integer size;
}
