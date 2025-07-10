package github.com.emreisler.erp_be.persistence.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class Book extends MyProduct {
    private String author;
}
