package github.com.emreisler.erp_be.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PhoneNumber {
    @Id
    private Long id;

    @Column(unique = true)
    private String number;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
