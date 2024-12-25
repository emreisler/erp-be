package github.com.emreisler.erp_be.controllers;

import github.com.emreisler.erp_be.dto.AttachStockRequest;
import github.com.emreisler.erp_be.dto.OperationDto;
import github.com.emreisler.erp_be.dto.PartDto;
import github.com.emreisler.erp_be.dto.StockDto;
import github.com.emreisler.erp_be.service.part.PartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/part")
public class PartController {

    private final PartService partService;

    public PartController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping
    public ResponseEntity<List<PartDto>> getAllParts() throws Exception {
        return ResponseEntity.ok(partService.GetAll());
    }

    @GetMapping("/project/{projectCode}")
    public ResponseEntity<List<PartDto>> getPartsByProjectCode(@PathVariable String projectCode) throws Exception {
        return ResponseEntity.ok(partService.GetByProjectCode(projectCode));
    }

    @GetMapping("/number/{number}")
    public ResponseEntity<PartDto> getPartByNumber(@PathVariable String number) throws Exception {
        return ResponseEntity.ok(partService.GetByNumber(number));
    }

    @GetMapping("/operation/{partNo}")
    public ResponseEntity<List<OperationDto>> getOperations(@PathVariable String partNo) throws Exception {
        return ResponseEntity.ok(partService.GetOperations(partNo));
    }

    @GetMapping("/stock/{partNo}")
    public ResponseEntity<List<StockDto>> getStocksByPartNo(@PathVariable String partNo) throws Exception {
        return ResponseEntity.ok(partService.getStocks(partNo));
    }

    @PostMapping
    public ResponseEntity<PartDto> createPart(@RequestBody PartDto partDto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(partService.Create(partDto));
    }

    @PutMapping("/operation/{partNumber}")
    public ResponseEntity<PartDto> AttachOperation(@PathVariable String partNumber, @RequestBody OperationDto operation) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(partService.AttachOperation(partNumber, operation));
    }

    @PutMapping("/stock/{partNumber}")
    public ResponseEntity<PartDto> addStock(@PathVariable String partNumber, @RequestBody AttachStockRequest attachStockRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(partService.attachStock(partNumber, attachStockRequest));
    }

    @DeleteMapping("/operation/{partNumber}")
    ResponseEntity<PartDto> DeleteOperation(@PathVariable String partNumber, @RequestParam int stepNumber) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(partService.DeleteOperation(partNumber, stepNumber));
    }

    @DeleteMapping("/{partNumber}")
    public ResponseEntity<String> deletePart(@PathVariable String partNumber) throws Exception {
        partService.Delete(partNumber);
        return ResponseEntity.ok("Part deleted successfully");
    }


}
