package github.com.emreisler.erp_be.persistence.entity;


import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class Person {
    @Id
    private long personId;
    private String name;
}
