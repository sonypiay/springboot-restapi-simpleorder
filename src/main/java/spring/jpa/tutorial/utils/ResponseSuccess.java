package spring.jpa.tutorial.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSuccess<T> {
    public T data;
    public String message;
    public Integer totalData = 0;
}
