package github.com.emreisler.erp_be.controllers;

import github.com.emreisler.erp_be.dto.ProductionOrderDto;
import github.com.emreisler.erp_be.dto.StampDto;
import github.com.emreisler.erp_be.service.operator.OperatorService;
import github.com.emreisler.erp_be.service.stamp.StampService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/stamp")
public class StampController {

    private final StampService stampService;
    private final OperatorService operatorService;


    public StampController(StampService stampService, OperatorService operatorService) {
        this.stampService = stampService;
        this.operatorService = operatorService;
    }

    @GetMapping("{poCode}")
    public ResponseEntity<List<StampDto>> getStamp(@PathVariable String poCode) {
        return ResponseEntity.ok(stampService.getByPoCode(poCode));
    }

    @PutMapping
    public ResponseEntity<ProductionOrderDto> stamp(@RequestBody StampDto stampDto) throws Exception {
        return ResponseEntity.ok(operatorService.stamp(stampDto));
    }
}
