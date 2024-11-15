package spring.jpa.tutorial.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
    public WebResponse<List<CategoriesResponse>> list() {
        List<CategoriesResponse> categoriesResponsesList = categoriesService.list();

        return WebResponse.<List<CategoriesResponse>>builder()
                .data(categoriesResponsesList)
                .build();
    }
}
