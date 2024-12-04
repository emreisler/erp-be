package github.com.emreisler.erp_be.dto;

import github.com.emreisler.erp_be.enums.CategoryType;

import java.util.List;
import java.util.UUID;

public class PartDto {

    private UUID uuid;
    private String number;
    private String name;
    private String projectCode;
    private CategoryType category;
    private List<StockDto> stocksList;
    private List<OperationDto> operationList;

    public PartDto(UUID uuid, String number, String name, String projectCode, CategoryType category, List<StockDto> stocksList, List<OperationDto> operationList) {
        this.uuid = uuid;
        this.number = number;
        this.name = name;
        this.projectCode = projectCode;
        this.category = category;
        this.stocksList = stocksList;
        this.operationList = operationList;
    }

    public PartDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<StockDto> getStocksList() {
        return stocksList;
    }

    public void setStocksList(List<StockDto> stocksList) {
        this.stocksList = stocksList;
    }

    public List<OperationDto> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<OperationDto> operationList) {
        this.operationList = operationList;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public CategoryType getCategory() {
        return category;
    }

    public void setCategory(CategoryType category) {
        this.category = category;
    }
}
