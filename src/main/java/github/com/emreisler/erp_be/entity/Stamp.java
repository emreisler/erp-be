package github.com.emreisler.erp_be.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "stamps",indexes = @Index(columnList = "poCode"))

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

    public Stamp(String poCode, int stepNumber, String stampedBy, LocalDateTime stampedAt) {
        this.poCode = poCode;
        this.stepNumber = stepNumber;
        this.stampedBy = stampedBy;
        this.stampedAt = stampedAt;
    }

    public Stamp() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getPoCode() {
        return poCode;
    }

    public void setPoCode(String poCode) {
        this.poCode = poCode;
    }

    public String getStampedBy() {
        return stampedBy;
    }

    public void setStampedBy(String stampedBy) {
        this.stampedBy = stampedBy;
    }

    public LocalDateTime getStampedAt() {
        return stampedAt;
    }

    public void setStampedAt(LocalDateTime stampedAt) {
        this.stampedAt = stampedAt;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }
}
