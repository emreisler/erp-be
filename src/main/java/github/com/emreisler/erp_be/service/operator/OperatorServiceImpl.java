package github.com.emreisler.erp_be.service.operator;

import github.com.emreisler.erp_be.dto.OperationDto;
import github.com.emreisler.erp_be.dto.ProductionOrderDto;
import github.com.emreisler.erp_be.dto.StampDto;
import github.com.emreisler.erp_be.exception.ErpRuntimeException;
import github.com.emreisler.erp_be.service.part.PartService;
import github.com.emreisler.erp_be.service.productionOrder.ProductionOrderService;
import github.com.emreisler.erp_be.service.stamp.StampService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;

@Service
public class OperatorServiceImpl implements OperatorService {

    private final ProductionOrderService productionOrderService;
    private final StampService stampService;
    private final PartService partService;


    public OperatorServiceImpl(ProductionOrderService productionOrderService, StampService stampService, PartService partService) {
        this.productionOrderService = productionOrderService;
        this.stampService = stampService;
        this.partService = partService;
    }

    @Override
    @Transactional
    public ProductionOrderDto stamp(StampDto stampDto) throws Exception {

        //todo validate should be handled in higher level
        //validate user exist
        if (stampDto.getProductionOrderCode().isEmpty()) {
            throw new IllegalArgumentException("Production order code is empty");
        }
        if (stampDto.getStepNumber() == 0) {
            throw new IllegalArgumentException("Step number is empty");
        }
        if (stampDto.getUserEmail().isEmpty()) {
            throw new IllegalArgumentException("User email is empty");
        }

        var prodOrder = productionOrderService.getByCode(stampDto.getProductionOrderCode());

        var part = partService.GetByNumber(prodOrder.getPartNumber());

        var operations = part.getOperationList().stream().sorted(Comparator.comparingInt(OperationDto::getSepNumber)).toList();

        var stepFound = false;
        for (OperationDto op : operations) {
            if (stepFound) {
                prodOrder.setCurrentStep(op.getSepNumber());
                prodOrder.setCurrentTaskCenter(op.getTaskCenterNo());
                break;
            }
            if (op.getSepNumber() == stampDto.getStepNumber()) {
                stepFound = true;
            }
        }

        if (!stepFound) {
            throw new IllegalArgumentException("Step number " + stampDto.getStepNumber() + " not found for part : " + prodOrder.getPartNumber());
        }

        try {
            stampService.stamp(stampDto);
            return productionOrderService.update(prodOrder);
        } catch (Exception e) {
            throw new ErpRuntimeException(e.getMessage());
        }

    }
}
