package github.com.emreisler.erp_be.controllers;

import github.com.emreisler.erp_be.dto.CreateProductionOrderRequest;
import github.com.emreisler.erp_be.dto.ProductionOrderDto;
import github.com.emreisler.erp_be.service.productionOrder.ProductionOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/production-orders")
public class ProductionOrderController {

    private final ProductionOrderService productionOrderService;

    public ProductionOrderController(ProductionOrderService productionOrderService) {
        this.productionOrderService = productionOrderService;
    }

    @GetMapping
    public ResponseEntity<List<ProductionOrderDto>> getAll() {
        return ResponseEntity.ok(productionOrderService.getAll());
    }

    @GetMapping("/by-part-number/{partNo}")
    public ResponseEntity<List<ProductionOrderDto>> getByPartNo(@PathVariable String partNo) {
        return ResponseEntity.ok(productionOrderService.getByPartNo(partNo));
    }

    @GetMapping("/by-task-center/{taskCenter}")
    public ResponseEntity<List<ProductionOrderDto>> getByCurrentTaskCenterNo(@PathVariable int taskCenter) {
        return ResponseEntity.ok(productionOrderService.getByCurrentTaskCenterNo(taskCenter));
    }

    @PostMapping
    public ResponseEntity<ProductionOrderDto> create(@RequestBody CreateProductionOrderRequest request) {
        return ResponseEntity.ok(productionOrderService.create(request));
    }
}
