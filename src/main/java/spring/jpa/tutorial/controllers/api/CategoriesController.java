package spring.jpa.tutorial.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import spring.jpa.tutorial.models.requests.CategoriesRequest;
import spring.jpa.tutorial.models.responses.CategoriesResponse;
import spring.jpa.tutorial.services.CategoriesService;
import spring.jpa.tutorial.utils.WebResponse;

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
    public WebResponse<CategoriesResponse> create(@RequestBody CategoriesRequest categoriesRequest) {
        CategoriesResponse categoriesResponse = categoriesService.create(categoriesRequest);

        return WebResponse.<CategoriesResponse>builder()
                .data(categoriesResponse)
                .build();
    }

    @GetMapping(
            path = "/api/categories"
    )
    public WebResponse<List<CategoriesResponse>> list(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "5") Integer size
    ) {
        CategoriesRequest.builder()
                .name(name)
                .page(page)
                .size(size)
                .build();
        List<CategoriesResponse> categoriesResponsesList = categoriesService.list();

        return WebResponse.<List<CategoriesResponse>>builder()
                .data(categoriesResponsesList)
                .build();
    }

    @PutMapping(
            path = "/api/categories/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<CategoriesResponse> update(@PathVariable("id") String id, CategoriesRequest request) {
        Integer categoryId = Integer.parseInt(id);

        request.setId(categoryId);
        CategoriesResponse response = categoriesService.update(request);

        return WebResponse.<CategoriesResponse>builder()
                .data(response)
                .build();
    }
}
