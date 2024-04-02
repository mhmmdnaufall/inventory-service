package mhmmdnaufall.inventoryservice.controller;

import lombok.RequiredArgsConstructor;
import mhmmdnaufall.inventoryservice.dto.InventoryResponse;
import mhmmdnaufall.inventoryservice.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        return inventoryService.isInStock(skuCode);
    }

}
