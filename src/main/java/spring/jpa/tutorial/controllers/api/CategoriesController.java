package spring.jpa.tutorial.controllers.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.jpa.tutorial.models.requests.CreateCategoriesRequest;
import spring.jpa.tutorial.models.requests.UpdateCategoriesRequest;
import spring.jpa.tutorial.models.responses.CategoriesResponse;
import spring.jpa.tutorial.services.CategoriesService;
import spring.jpa.tutorial.utils.Pagination;
import spring.jpa.tutorial.utils.ResponseSuccess;
import spring.jpa.tutorial.utils.ResponseSuccessWithPaging;

import java.util.List;

@RestController
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @PostMapping(
            path = "/api/categories",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseSuccess<CategoriesResponse>> create(@Valid @RequestBody CreateCategoriesRequest request) {
        CategoriesResponse response = categoriesService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseSuccess.<CategoriesResponse>builder()
                        .data(response)
                        .message("success")
                        .build()
        );
    }

    @GetMapping("/api/categories")
    public ResponseEntity<ResponseSuccessWithPaging<List<CategoriesResponse>>> list(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(value = "sort_type", required = false, defaultValue = "asc") String sortType,
            @RequestParam(value = "sort_by", required = false, defaultValue = "id") String sortBy,
            Pagination pagination
    ) {
        pagination.setCurrentPage(page);
        pagination.setSize(size);

        Page<CategoriesResponse> data = categoriesService.list(pagination, sortType, sortBy);

        return ResponseEntity.ok(
                ResponseSuccessWithPaging.<List<CategoriesResponse>>builder()
                        .data(data.getContent())
                        .totalData(data.getSize())
                        .message("success")
                        .paging(Pagination.builder()
                                .currentPage(data.getNumber())
                                .totalPage(data.getTotalPages())
                                .size(data.getSize())
                                .build()
                        )
                        .build()
        );
    }

    @PutMapping(
            path = "/api/categories/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseSuccess<CategoriesResponse>> update(@PathVariable("id") String id, @Valid @RequestBody UpdateCategoriesRequest request) {
        Integer categoryId = Integer.parseInt(id);

        request.setId(categoryId);
        CategoriesResponse response = categoriesService.update(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseSuccess.<CategoriesResponse>builder()
                        .data(response)
                        .message("success")
                        .build()
        );
    }

    @GetMapping("/api/categories/{id}")
    public ResponseEntity<ResponseSuccess<CategoriesResponse>> getById(@PathVariable("id") String id) {
        Integer categoryId = Integer.parseInt(id);

        CategoriesResponse response = categoriesService.getById(categoryId);
        return ResponseEntity.ok(
                ResponseSuccess.<CategoriesResponse>builder()
                        .data(response)
                        .message("OK")
                        .build()
        );
    }

    @DeleteMapping("/api/categories/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        categoriesService.delete(id);

        return ResponseEntity.ok(
                ResponseSuccess.builder()
                        .message("success")
                        .build()
        );
    }
}
