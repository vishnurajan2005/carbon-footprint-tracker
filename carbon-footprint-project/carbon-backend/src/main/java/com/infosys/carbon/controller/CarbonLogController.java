package com.infosys.carbon.controller;

import com.infosys.carbon.entity.CarbonLog;
import com.infosys.carbon.entity.User;
import com.infosys.carbon.repository.CarbonLogRepository;
import com.infosys.carbon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carbon")
@CrossOrigin(origins = "*")
public class CarbonLogController {

    @Autowired
    private CarbonLogRepository carbonLogRepository;

    @Autowired
    private UserRepository userRepository;

    // STEP 6 API
    @GetMapping("/history/{userId}")
    public List<CarbonLog> getCarbonHistory(@PathVariable Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return carbonLogRepository.findByUser(user);
    }
}

