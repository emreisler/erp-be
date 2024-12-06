package github.com.emreisler.erp_be.service.stamp;

import github.com.emreisler.erp_be.dto.StampDto;
import github.com.emreisler.erp_be.exception.ErpRuntimeException;

import java.util.List;

public interface StampService {
    StampDto stamp(StampDto stampDto) throws ErpRuntimeException;
    List<StampDto> getByPoCode(String code) throws ErpRuntimeException;
}
