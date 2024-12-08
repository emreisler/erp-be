package github.com.emreisler.erp_be.service.operator;

import github.com.emreisler.erp_be.dto.OperationDto;
import github.com.emreisler.erp_be.dto.ProductionOrderDto;
import github.com.emreisler.erp_be.dto.StampDto;
import github.com.emreisler.erp_be.exception.BadRequestException;
import github.com.emreisler.erp_be.exception.ErpRuntimeException;
import github.com.emreisler.erp_be.service.part.PartService;
import github.com.emreisler.erp_be.service.productionOrder.ProductionOrderService;
import github.com.emreisler.erp_be.service.stamp.StampService;
import github.com.emreisler.erp_be.validators.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;

@Service
public class OperatorServiceImpl implements OperatorService {

    private final ProductionOrderService productionOrderService;
    private final StampService stampService;
    private final PartService partService;
    private final Validator<StampDto> validator;


    public OperatorServiceImpl(ProductionOrderService productionOrderService, StampService stampService, PartService partService, Validator<StampDto> validator) {
        this.productionOrderService = productionOrderService;
        this.stampService = stampService;
        this.partService = partService;
        this.validator = validator;
    }

    @Override
    @Transactional
    public ProductionOrderDto stamp(StampDto stampDto) throws Exception {

        validator.validate(stampDto);

        var prodOrder = productionOrderService.getByCode(stampDto.getProductionOrderCode());

        var part = partService.GetByNumber(prodOrder.getPartNumber());

        if (part.getOperationList().isEmpty()) {
            throw new BadRequestException("part has no operation to stamp");
        }

        if (stampService.isStamped(stampDto.getProductionOrderCode(), stampDto.getStepNumber())){
            throw new BadRequestException("step is already stamped");
        }

        var sortedOperations = part.getOperationList().stream().sorted(Comparator.comparingInt(OperationDto::getSepNumber)).toList();

        int previousStep = 0;
        var stepExist = false;
        for (var operation : sortedOperations) {
            if (stampDto.getStepNumber() == operation.getSepNumber()){
                stepExist = true;
            }
            if (stepExist){
                break;
            }
            previousStep = operation.getSepNumber();
        }

        if (!stepExist){
            throw new BadRequestException("step not exist");
        }

        if (previousStep != 0 && !stampService.isStamped(stampDto.getProductionOrderCode(), previousStep)){
            throw new BadRequestException("previous step is not stamped");
        }

        try {
            stampService.stamp(stampDto);
            return productionOrderService.update(prodOrder);
        } catch (Exception e) {
            throw new ErpRuntimeException(e.getMessage());
        }

    }
}
