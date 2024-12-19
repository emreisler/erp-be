package github.com.emreisler.erp_be.converters;

import github.com.emreisler.erp_be.dto.StampDto;
import github.com.emreisler.erp_be.entity.Stamp;

import java.time.LocalDateTime;

public class StampConverter {

    public static StampDto toDto(Stamp stamp) {
        if (stamp == null) {
            return null;
        }
        return new StampDto(stamp.getStampedBy(), stamp.getPoCode(), stamp.getStepNumber());
    }

    public static Stamp toEntity(StampDto stampDto) {
        if (stampDto == null) {
            return null;
        }
        var stamp = new Stamp();
        stamp.setStampedAt(LocalDateTime.now());
        stamp.setStampedBy(stampDto.getUserEmail());
        stamp.setPoCode(stampDto.getProductionOrderCode());
        stamp.setStepNumber(stampDto.getStepNumber());
        return stamp;
    }
}
