package github.com.emreisler.erp_be.converters;

import github.com.emreisler.erp_be.application.dto.StampDto;
import github.com.emreisler.erp_be.persistence.entity.Stamp;

import java.time.LocalDateTime;

public class StampConverter {

    public static StampDto toDto(Stamp stamp) {
        if (stamp == null) {
            return null;
        }
        return new StampDto(stamp.getStampedBy(), stamp.getProductionOrder().getCode(), stamp.getStepNumber());
    }

    public static Stamp toEntity(StampDto stampDto) {
        if (stampDto == null) {
            return null;
        }
        var stamp = new Stamp();
        stamp.setStampedAt(LocalDateTime.now());
        stamp.setStampedBy(stampDto.getUserEmail());
        stamp.setProductionOrder(stamp.getProductionOrder());
        stamp.setStepNumber(stampDto.getStepNumber());
        return stamp;
    }
}
