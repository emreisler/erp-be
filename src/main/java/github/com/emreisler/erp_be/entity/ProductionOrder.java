package github.com.emreisler.erp_be.entity;

import github.com.emreisler.erp_be.enums.ProductionOrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class ProductionOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private UUID orderId;

    @Column(nullable = false, unique = true)
    private String code;

    @NotNull
    private String partNumber;

    @Positive
    private int quantity;

    @Enumerated(EnumType.ORDINAL)
    private ProductionOrderStatus status;

    @Positive
    private int currentStep;

    @Positive
    private int totalSteps;

    @Positive
    private int currentTaskCenter;

    @Column(nullable = false)
    private LocalDate endDate;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
