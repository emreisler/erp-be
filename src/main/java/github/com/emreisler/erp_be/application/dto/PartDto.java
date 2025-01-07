package github.com.emreisler.erp_be.application.dto;

import github.com.emreisler.erp_be.domain.enums.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PartDto {

    private UUID uuid;
    private String number;
    private String name;
    private String projectCode;
    private CategoryType category;
    private List<StockDto> stocksList;
    private List<OperationDto> operationList;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
