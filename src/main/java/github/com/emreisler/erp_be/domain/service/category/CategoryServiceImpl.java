package github.com.emreisler.erp_be.domain.service.category;


import github.com.emreisler.erp_be.application.dto.CategoryDto;
import github.com.emreisler.erp_be.converters.CategoryConverter;
import github.com.emreisler.erp_be.persistence.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDto> getAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryConverter::toDto)
                .collect(Collectors.toList());
    }
}
