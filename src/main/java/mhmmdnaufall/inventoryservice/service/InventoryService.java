package mhmmdnaufall.inventoryservice.service;

import mhmmdnaufall.inventoryservice.dto.InventoryResponse;

import java.util.List;

public interface InventoryService {

    List<InventoryResponse> isInStock(List<String> skuCode);

}
