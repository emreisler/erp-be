package github.com.emreisler.erp_be.application.controllers;

import github.com.emreisler.erp_be.application.dto.StockDto;
import github.com.emreisler.erp_be.domain.service.stock.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class StockController {

    private final StockService stockService;

    StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/stock")
    public ResponseEntity<List<StockDto>> GetStocks() throws Exception {
        return ResponseEntity.ok(this.stockService.GetAll());
    }


    @GetMapping("/stock/{code}")
    public ResponseEntity<StockDto> GetStockByCode(@PathVariable String code) throws Exception {
        return ResponseEntity.ok(this.stockService.GetByCode(code));
    }

    @PostMapping("/stock")
    public ResponseEntity<StockDto> AddStock(@RequestBody StockDto stockDto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.stockService.Create(stockDto));
    }

    @PutMapping("/stock/{code}")
    public ResponseEntity<StockDto> UpdateStock(@PathVariable String code, @RequestBody StockDto stockDto) throws Exception {
        return ResponseEntity.ok(this.stockService.Update(code, stockDto));
    }

    @DeleteMapping("/stock/{code}")
    public ResponseEntity<String> DeleteStock(@PathVariable String code) throws Exception {
        stockService.Delete(code);
        return ResponseEntity.ok("Stock deleted");
    }
}
