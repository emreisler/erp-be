package github.com.emreisler.erp_be.dto;

import github.com.emreisler.erp_be.enums.CategoryType;


public class CategoryDto {
    private CategoryType type;

    public CategoryDto(CategoryType type) {
        this.type = type;
    }

    public CategoryDto() {
    }

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }
}
