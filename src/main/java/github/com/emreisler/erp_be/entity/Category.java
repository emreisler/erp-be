package github.com.emreisler.erp_be.entity;

import github.com.emreisler.erp_be.enums.CategoryType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING) // Store enum value as String in the database
    @Column(nullable = false, unique = true)
    private CategoryType type;

    public Category() {
    }

    public Category(CategoryType type) {
        this.type = type;
    }

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
