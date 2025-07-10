package github.com.emreisler.erp_be.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@Entity(name = "products")
@DiscriminatorColumn(name = "product_type",
        discriminatorType = DiscriminatorType.INTEGER)
public class MyProduct {
    @Id
    private long productId;
    private String name;

    // constructor, getters, setters
}
