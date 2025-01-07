package github.com.emreisler.erp_be.application.controllers;

import github.com.emreisler.erp_be.application.dto.AssemblyDto;
import github.com.emreisler.erp_be.application.dto.AttachPartDto;
import github.com.emreisler.erp_be.application.dto.AttachedStockDto;
import github.com.emreisler.erp_be.application.dto.OperationDto;
import github.com.emreisler.erp_be.domain.service.assembly.AssemblyService;
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
    public ResponseEntity<List<AttachPartDto>> getParts(@PathVariable String assemblyNo) throws Exception {
        return ResponseEntity.ok(assemblyService.getPartsByAssemblyNo(assemblyNo));
    }


    @GetMapping("/operation/{assemblyNo}")
    public ResponseEntity<List<OperationDto>> getOperations(@PathVariable String assemblyNo) throws Exception {
        return ResponseEntity.ok(assemblyService.getOperations(assemblyNo));
    }

    @GetMapping("/stock/{assemblyNo}")
    public ResponseEntity<List<AttachedStockDto>> getStocks(@PathVariable String assemblyNo) throws Exception {
        return ResponseEntity.ok(assemblyService.getStocks(assemblyNo));
    }

    @PostMapping
    public ResponseEntity<AssemblyDto> addAssembly(@RequestBody AssemblyDto assemblyDto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(assemblyService.Create(assemblyDto));
    }

    @PutMapping("/stock/{assemblyNumber}")
    public ResponseEntity<AssemblyDto> attachStock(@PathVariable String assemblyNumber, @RequestBody AttachedStockDto attachedStockDto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(assemblyService.attachStock(assemblyNumber, attachedStockDto));
    }

    @PutMapping("/part/{assemblyNumber}")
    public ResponseEntity<AssemblyDto> attachPart(@PathVariable String assemblyNumber, @RequestBody AttachPartDto attachPartDto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(assemblyService.attachPart(assemblyNumber, attachPartDto));
    }

    @PutMapping("/operation/{assemblyNumber}")
    public ResponseEntity<AssemblyDto> attachOperation(@PathVariable String assemblyNumber, @RequestBody OperationDto operation) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(assemblyService.AttachOperation(assemblyNumber, operation));
    }

    @PutMapping("/operation")
    public ResponseEntity<AssemblyDto> updateOperation(@PathVariable String assemblyNumber, @RequestBody OperationDto operationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(assemblyService.updateOperation(assemblyNumber, operationDto));
    }
}
