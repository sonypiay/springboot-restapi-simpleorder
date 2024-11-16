package spring.jpa.tutorial.utils;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSuccessWithPaging<T> {
    public T data;
    public String message;
    public Integer totalData = 0;
    private Pagination paging;
}
