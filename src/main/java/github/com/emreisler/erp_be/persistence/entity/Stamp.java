package github.com.emreisler.erp_be.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "stamps", indexes = @Index(columnList = "production_order_code"))
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Stamp {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "production_order_code")
    private ProductionOrder productionOrder;

    private int stepNumber;

    @Column(nullable = false)
    private String stampedBy;

    @Column(nullable = false)
    private LocalDateTime stampedAt;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
