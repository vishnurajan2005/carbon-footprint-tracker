package com.infosys.carbon.controller;

import com.infosys.carbon.entity.Badge;
import com.infosys.carbon.entity.User;
import com.infosys.carbon.repository.UserRepository;
import com.infosys.carbon.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/badges")
@CrossOrigin(origins = "*")
public class BadgeController {

    @Autowired
    private BadgeService badgeService;

    @Autowired
    private UserRepository userRepository;

    // ✅ Get all badges of a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Badge>> getUserBadges(@PathVariable Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(
                badgeService.getUserBadges(user)
        );
    }

    // ✅ Manually award badge (for testing / admin)
    @PostMapping("/award")
    public ResponseEntity<Badge> awardBadge(
            @RequestParam Long userId,
            @RequestParam String badgeName,
            @RequestParam String description) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Badge badge = badgeService.awardBadge(user, badgeName, description);

        if (badge == null) {
            return ResponseEntity.badRequest().build(); // already awarded
        }

        return ResponseEntity.ok(badge);
    }
}




