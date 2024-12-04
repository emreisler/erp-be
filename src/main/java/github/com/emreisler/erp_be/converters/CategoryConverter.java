package github.com.emreisler.erp_be.converters;

import github.com.emreisler.erp_be.dto.CategoryDto;
import github.com.emreisler.erp_be.entity.Category;

public class CategoryConverter {


    public static Category toDto(CategoryDto dto) {
        Category category = new Category();
        category.setType(dto.getType());
        return category;
    }

    public static CategoryDto toDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setType(category.getType());
        return dto;
    }
}
