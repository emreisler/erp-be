package github.com.emreisler.erp_be.domain.model;

import github.com.emreisler.erp_be.domain.enums.CategoryType;
import github.com.emreisler.erp_be.domain.model.value.PartNumber;
import github.com.emreisler.erp_be.domain.model.value.ProjectCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//Aggregate Root
public class Part {
    private UUID uuid;
    private PartNumber number;
    private String name;
    private ProjectCode projectCode;
    private CategoryType category;
    private List<Stock> stocksList;
    private List<Operation> operationList;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Part(PartNumber partNumber, String partName, ProjectCode projectCode, CategoryType category) {
        this.uuid = UUID.randomUUID();
        this.number = partNumber;
        this.name = partName;
        this.projectCode = projectCode;
        this.category = category;
        this.stocksList = new ArrayList<>();
        this.operationList = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = createdAt;
    }

    public void addStock(Stock stock) {
        this.stocksList.add(stock);
    }

    public void addOperation(Operation operation) {
        this.operationList.add(operation);
    }


}