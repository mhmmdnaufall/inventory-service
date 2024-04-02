package mhmmdnaufall.inventoryservice.service.impl;

import lombok.RequiredArgsConstructor;
import mhmmdnaufall.inventoryservice.dto.InventoryResponse;
import mhmmdnaufall.inventoryservice.repository.InventoryRepository;
import mhmmdnaufall.inventoryservice.service.InventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @Override
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory -> new InventoryResponse(inventory.getSkuCode(), inventory.getQuantity() > 0))
                .toList();
    }

}
