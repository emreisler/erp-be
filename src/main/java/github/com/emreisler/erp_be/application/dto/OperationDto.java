package github.com.emreisler.erp_be.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationDto {

    private UUID operationId;
    private String partNumber;
    private String assemblyNumber;
    private int stepNumber;
    private String description;
    private String imageUrl;
    private int taskCenterNo;


}
