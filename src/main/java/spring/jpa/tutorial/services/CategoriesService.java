package spring.jpa.tutorial.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.criteria.Predicate;
import spring.jpa.tutorial.exceptions.BadRequestException;
import spring.jpa.tutorial.exceptions.NotFoundException;
import spring.jpa.tutorial.models.entities.Categories;
import spring.jpa.tutorial.models.repository.CategoriesRepository;
import spring.jpa.tutorial.models.requests.CreateCategoriesRequest;
import spring.jpa.tutorial.models.requests.SearchCategoriesRequest;
import spring.jpa.tutorial.models.requests.UpdateCategoriesRequest;
import spring.jpa.tutorial.models.responses.CategoriesResponse;
import spring.jpa.tutorial.utils.Pagination;
import spring.jpa.tutorial.utils.ValidationRequest;

import java.util.ArrayList;
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
        if(categoriesRepository.existsByName(request.getName())) {
            throw new BadRequestException("Nama kategori sudah terdaftar");
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
    public Page<CategoriesResponse> list(SearchCategoriesRequest searchCategoriesRequest, Pagination pagination, String sortType, String sortBy) {
        Sort sort = sortType.equals("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pagination.getCurrentPage(), pagination.getSize(), sort);
        Page<Categories> categories = categoriesRepository.findAll((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(Objects.nonNull(searchCategoriesRequest.getName())) {
                predicates.add(builder.like( root.get("name"), "%" + searchCategoriesRequest.getName() + "%" ));
            }

            return query.where(
                    predicates.toArray(new jakarta.persistence.criteria.Predicate[]{})
            ).getRestriction();
        } , pageable);
        List<CategoriesResponse> responses = categories
                .getContent()
                .stream()
                .map(this::toResponse)
                .toList();

        return new PageImpl<>(responses, pageable, categories.getTotalElements());
    }

    @Transactional
    public CategoriesResponse update(UpdateCategoriesRequest request) {
        Categories categories = categoriesRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException("Kategori tidak ditemukan"));

        if(categoriesRepository.existsByNameWithid(request.getName(), categories.getId())) {
            throw new BadRequestException("Nama kategori sudah terdaftar");
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
                .orElseThrow(() -> new NotFoundException("Kategori tidak ditemukan"));

        return toResponse(categories);
    }

    @Transactional
    public void delete(Integer id) {
        if( ! categoriesRepository.existsById(id) ) throw new NotFoundException("Kategori tidak ditemukan");
        categoriesRepository.deleteById(id);
    }
}
