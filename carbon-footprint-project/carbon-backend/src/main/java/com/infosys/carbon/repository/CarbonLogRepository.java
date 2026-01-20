package com.infosys.carbon.repository;

import com.infosys.carbon.entity.CarbonLog;
import com.infosys.carbon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarbonLogRepository extends JpaRepository<CarbonLog, Long> {

    List<CarbonLog> findByUser(User user);
}

