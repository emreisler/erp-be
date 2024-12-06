package github.com.emreisler.erp_be.controllers;

import github.com.emreisler.erp_be.dto.StampDto;
import github.com.emreisler.erp_be.service.stamp.StampService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/stamp")
public class StampController {

    private final StampService stampService;

    public StampController(StampService stampService) {
        this.stampService = stampService;
    }

    @GetMapping("{poCode}")
    public ResponseEntity<List<StampDto>> getStamp(@PathVariable String poCode) {
        return ResponseEntity.ok(stampService.getByPoCode(poCode));
    }
}
