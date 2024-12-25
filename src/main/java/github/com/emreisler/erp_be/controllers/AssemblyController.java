package github.com.emreisler.erp_be.controllers;

import github.com.emreisler.erp_be.dto.*;
import github.com.emreisler.erp_be.service.assembly.AssemblyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/assembly")
public class AssemblyController {

    private final AssemblyService assemblyService;

    public AssemblyController(AssemblyService assemblyService) {
        this.assemblyService = assemblyService;
    }

    @GetMapping
    public ResponseEntity<List<AssemblyDto>> getAllAssemblies() throws Exception {
        return ResponseEntity.ok(assemblyService.getAll());
    }

    @GetMapping("/part/{assemblyNo}")
    public ResponseEntity<List<PartDto>> getParts(@PathVariable String assemblyNo) throws Exception {
        return ResponseEntity.ok(assemblyService.getPartsByAssemblyNo(assemblyNo));
    }


    @GetMapping("/operation/{assemblyNo}")
    public ResponseEntity<List<OperationDto>> getOperations(@PathVariable String assemblyNo) throws Exception {
        return ResponseEntity.ok(assemblyService.getOperations(assemblyNo));
    }

    @GetMapping("/stock/{assemblyNo}")
    public ResponseEntity<List<StockDto>> getStocks(@PathVariable String assemblyNo) throws Exception {
        return ResponseEntity.ok(assemblyService.getStocks(assemblyNo));
    }

    @PostMapping
    public ResponseEntity<AssemblyDto> addAssembly(@RequestBody AssemblyDto assemblyDto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(assemblyService.Create(assemblyDto));
    }

    @PutMapping("/stock/{assemblyNumber}")
    public ResponseEntity<AssemblyDto> AttachOperation(@PathVariable String assemblyNumber, @RequestBody AttachStockRequest attachStockRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(assemblyService.attachStock(assemblyNumber, attachStockRequest));
    }

    @PutMapping("/operation/{assemblyNumber}")
    public ResponseEntity<AssemblyDto> AttachStock(@PathVariable String assemblyNumber, @RequestBody OperationDto operation) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(assemblyService.AttachOperation(assemblyNumber, operation));
    }
}
