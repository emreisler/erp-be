package github.com.emreisler.erp_be.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Part {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uuid;

    @Column(unique = true, nullable = false)
    private String number;

    @Column(nullable = false)
    private String name;

    private String projectCode;

    @ManyToMany
    private List<Stock> stocksList;

    @OneToMany(mappedBy = "part", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Operation> operationList;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Stock> getStocksList() {
        return stocksList;
    }

    public void setStocksList(List<Stock> stocksList) {
        this.stocksList = stocksList;
    }

    public List<Operation> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<Operation> operationList) {
        this.operationList = operationList;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }
}
