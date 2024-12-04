package github.com.emreisler.erp_be.service.category;


import github.com.emreisler.erp_be.converters.CategoryConverter;
import github.com.emreisler.erp_be.dto.CategoryDto;
import github.com.emreisler.erp_be.repository.CategoryRepository;
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
