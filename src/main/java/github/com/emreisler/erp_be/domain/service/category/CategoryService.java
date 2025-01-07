package github.com.emreisler.erp_be.domain.service.category;

import github.com.emreisler.erp_be.application.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAll();
}
