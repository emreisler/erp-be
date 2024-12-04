package github.com.emreisler.erp_be.service.category;

import github.com.emreisler.erp_be.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAll();
}
