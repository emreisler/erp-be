package github.com.emreisler.erp_be.controllers;

import github.com.emreisler.erp_be.dto.StockDto;
import github.com.emreisler.erp_be.service.stock.StockService;
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

    @DeleteMapping("/stock/{uuid}")
    public ResponseEntity<String> DeleteStock(@PathVariable String uuid) throws Exception {
        stockService.Delete(uuid);
        return ResponseEntity.ok("Stock deleted");
    }
}
