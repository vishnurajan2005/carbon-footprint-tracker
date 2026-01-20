package com.infosys.carbon.repository;

import com.infosys.carbon.entity.MarketplaceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketplaceRepository
        extends JpaRepository<MarketplaceItem, Long> {
}

