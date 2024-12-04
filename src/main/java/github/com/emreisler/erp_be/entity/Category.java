package github.com.emreisler.erp_be.entity;

import github.com.emreisler.erp_be.enums.CategoryType;
import jakarta.persistence.*;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING) // Store enum value as String in the database
    @Column(nullable = false, unique = true)
    private CategoryType type;

    public Category(CategoryType type) {
        this.type = type;
    }

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }
}
