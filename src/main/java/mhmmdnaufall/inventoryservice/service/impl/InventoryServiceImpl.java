package mhmmdnaufall.inventoryservice.service.impl;

import lombok.RequiredArgsConstructor;
import mhmmdnaufall.inventoryservice.repository.InventoryRepository;
import mhmmdnaufall.inventoryservice.service.InventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @Override
    public boolean isInStock(String skuCode) {
        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }

}
