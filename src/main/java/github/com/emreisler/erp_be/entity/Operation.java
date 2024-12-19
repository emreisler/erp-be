package github.com.emreisler.erp_be.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Operation {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "part_id")
    private Part part;

    @ManyToOne
    @JoinColumn(name = "assembly_id")
    private Assembly assembly;

    @Column(nullable = false)
    private int sepNumber;

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
