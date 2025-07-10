package github.com.emreisler.erp_be.persistence.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sequence_id")
    private Sequence sequence;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String content;

}
