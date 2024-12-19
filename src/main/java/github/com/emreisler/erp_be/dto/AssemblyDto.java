package github.com.emreisler.erp_be.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class AssemblyDto {
    private UUID uuid;
    private String number;
    private String name;
    private String projectCode;
    private List<PartDto> partList;
    private List<OperationDto> operationList;
    private List<StockDto> stockList;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
