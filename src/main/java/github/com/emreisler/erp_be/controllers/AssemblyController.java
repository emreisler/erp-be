package github.com.emreisler.erp_be.controllers;

import github.com.emreisler.erp_be.dto.AssemblyDto;
import github.com.emreisler.erp_be.dto.OperationDto;
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
    public ResponseEntity<List<AssemblyDto>> getAllParts() throws Exception {
        return ResponseEntity.ok(assemblyService.getAll());
    }

    @PostMapping
    public ResponseEntity<AssemblyDto> addAssembly(@RequestBody AssemblyDto assemblyDto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(assemblyService.Create(assemblyDto));
    }

    @PutMapping("/operation/{assemblyNumber}")
    public ResponseEntity<AssemblyDto> AttachOperation(@PathVariable String assemblyNumber, @RequestBody OperationDto operation) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(assemblyService.AttachOperation(assemblyNumber, operation));
    }
}
