package github.com.emreisler.erp_be.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AssemblyDto {
    private UUID uuid;
    private String number;
    private String name;
    private String projectCode;
    private List<AttachPartDto> attachParts;
    private List<OperationDto> operationList;
    private List<AttachedStockDto> attachedStocks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
