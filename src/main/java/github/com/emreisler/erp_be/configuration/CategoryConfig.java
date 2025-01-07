package github.com.emreisler.erp_be.configuration;

import github.com.emreisler.erp_be.domain.enums.CategoryType;
import github.com.emreisler.erp_be.persistence.entity.Category;
import github.com.emreisler.erp_be.persistence.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryConfig {

    @Bean
    CommandLineRunner initCategories(CategoryRepository categoryRepository) {
        return args -> {
            if (categoryRepository.count() == 0) {
                categoryRepository.save(new Category(CategoryType.SHEET_METAL));
                categoryRepository.save(new Category(CategoryType.MACHINING));
                categoryRepository.save(new Category(CategoryType.COMPOSITE));
            }
        };
    }
}
