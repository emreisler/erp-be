package github.com.emreisler.erp_be.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AttachedPart {

    @Id
    @GeneratedValue
    private Long id;

    private String partNumber;
    private int quantity;


}
