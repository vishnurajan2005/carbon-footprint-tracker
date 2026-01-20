package com.infosys.carbon.controller;

import com.infosys.carbon.entity.MarketplaceItem;
import com.infosys.carbon.repository.MarketplaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marketplace")
@CrossOrigin("*")
public class MarketplaceController {

    @Autowired
    private MarketplaceRepository marketplaceRepository;

    @GetMapping
    public List<MarketplaceItem> getAllItems() {
        return marketplaceRepository.findAll();
    }

    // TEMP: add item (for testing)
    @PostMapping
    public MarketplaceItem addItem(@RequestBody MarketplaceItem item) {
        return marketplaceRepository.save(item);
    }
}

