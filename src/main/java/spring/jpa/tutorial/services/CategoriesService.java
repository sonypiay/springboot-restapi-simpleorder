package spring.jpa.tutorial.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import spring.jpa.tutorial.models.entities.Categories;
import spring.jpa.tutorial.models.repository.CategoriesRepository;
import spring.jpa.tutorial.models.requests.CategoriesRequest;
import spring.jpa.tutorial.models.responses.CategoriesResponse;
import spring.jpa.tutorial.utils.ValidationRequest;

import java.util.Date;
import java.util.List;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private ValidationRequest validationRequest;

    private CategoriesResponse toResponse(Categories categories) {
        return CategoriesResponse.builder()
                .id(categories.getId())
                .name(categories.getName())
                .description(categories.getDescription())
                .publish(categories.isPublish())
                .createdAt(categories.getCreatedAt())
                .updatedAt(categories.getUpdatedAt())
                .build();
    }

    @Transactional
    public CategoriesResponse create(CategoriesRequest request) {
        validationRequest.validate(request);

        if(categoriesRepository.existsByName(request.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nama kategori sudah terdaftar");
        }

        Categories categories = new Categories();
        categories.setName(request.getName());
        categories.setDescription(request.getDescription());
        categories.setPublish(request.isPublish());
        categories.setCreatedAt(new Date());
        categories.setUpdatedAt(new Date());

        categoriesRepository.save(categories);
        return toResponse(categories);
    }

    @Transactional(readOnly = true)
    public List<CategoriesResponse> list() {
        List<Categories> categories = categoriesRepository.findAll();

        return categories.stream()
                .map(this::toResponse)
                .toList();
    }
}
