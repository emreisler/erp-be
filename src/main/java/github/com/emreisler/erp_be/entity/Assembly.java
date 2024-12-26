package github.com.emreisler.erp_be.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Assembly {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uuid;

    @Column(unique = true, nullable = false)
    private String number;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String projectCode;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<AttachedPart> attachedParts;

    @OneToMany(mappedBy = "assembly", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Operation> operationList;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<AttachedStock> attachedStocks;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
