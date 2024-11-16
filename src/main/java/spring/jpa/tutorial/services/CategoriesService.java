package spring.jpa.tutorial.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import spring.jpa.tutorial.models.entities.Categories;
import spring.jpa.tutorial.models.repository.CategoriesRepository;
import spring.jpa.tutorial.models.requests.CreateCategoriesRequest;
import spring.jpa.tutorial.models.requests.UpdateCategoriesRequest;
import spring.jpa.tutorial.models.responses.CategoriesResponse;
import spring.jpa.tutorial.utils.Pagination;
import spring.jpa.tutorial.utils.ValidationRequest;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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
    public CategoriesResponse create(CreateCategoriesRequest request) {
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
    public Page<CategoriesResponse> list(Pagination pagination, String sortType, String sortBy) {
        Sort sort = sortType.equals("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pagination.getCurrentPage(), pagination.getSize(), sort);
        Page<Categories> categories = categoriesRepository.findAll(pageable);
        List<CategoriesResponse> responses = categories
                .getContent()
                .stream()
                .map(this::toResponse)
                .toList();

        return new PageImpl<>(responses, pageable, categories.getTotalElements());
    }

    @Transactional
    public CategoriesResponse update(UpdateCategoriesRequest request) {
        validationRequest.validate(request);

        Categories categories = categoriesRepository.findById(request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Kategori tidak ditemukan"));

        if(categoriesRepository.existsByNameWithid(request.getName(), categories.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nama kategori sudah terdaftar");
        }

        categories.setName(request.getName());
        categories.setDescription(request.getDescription());
        categories.setPublish(request.isPublish());
        categories.setUpdatedAt(new Date());
        categoriesRepository.save(categories);

        return toResponse(categories);
    }

    @Transactional(readOnly = true)
    public CategoriesResponse getById(Integer id) {
        Categories categories = categoriesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Kategori tidak ditemukan"));

        return toResponse(categories);
    }

    @Transactional
    public void delete(Integer id) {
        if( ! categoriesRepository.existsById(id) ) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kategori tidak ditemukan");
        categoriesRepository.deleteById(id);
    }
}
