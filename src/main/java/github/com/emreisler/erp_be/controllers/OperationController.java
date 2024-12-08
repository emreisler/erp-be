package github.com.emreisler.erp_be.controllers;

import github.com.emreisler.erp_be.dto.OperationDto;
import github.com.emreisler.erp_be.service.operations.OperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/operations")
public class OperationController {

    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping("/part-no/{partNo}")
    public ResponseEntity<List<OperationDto>> getByPartNumber(@PathVariable String partNo) throws Exception {
        return ResponseEntity.ok(operationService.getByPartNumber(partNo));
    }

}
