package github.com.emreisler.erp_be.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "stamps",indexes = @Index(columnList = "poCode"))
@Data
//@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Stamp {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String poCode;

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
