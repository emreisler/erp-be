package github.com.emreisler.erp_be.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Operation {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private UUID operationId;

    @ManyToOne
    @JoinColumn(name = "part_id")
    private Part part;

    @ManyToOne
    @JoinColumn(name = "assembly_id")
    private Assembly assembly;

    @Column(nullable = false)
    private int stepNumber;

    @Column(nullable = false)
    private String description;

    @Column(nullable = true)
    private String imageUrl;

    @Column(nullable = false)
    private int taskCenterNo;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
