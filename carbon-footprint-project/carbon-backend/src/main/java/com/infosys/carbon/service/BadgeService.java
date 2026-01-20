package com.infosys.carbon.service;

import com.infosys.carbon.entity.Badge;
import com.infosys.carbon.entity.User;
import com.infosys.carbon.repository.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BadgeService {

    @Autowired
    private BadgeRepository badgeRepository;

    // ✅ Used by GoalService (Task 5 – automation)
    public void awardIfNotExists(User user, String badgeName, String description) {

        if (badgeRepository.existsByUserAndBadgeName(user, badgeName)) {
            return; // prevent duplicate
        }

        Badge badge = new Badge();
        badge.setUser(user);
        badge.setBadgeName(badgeName);
        badge.setDescription(description);

        badgeRepository.save(badge);
    }

    // ✅ Used by BadgeController (get all badges of a user)
    public List<Badge> getUserBadges(User user) {
        return badgeRepository.findByUser(user);
    }

    // ✅ Used by BadgeController (manual award API)
    public Badge awardBadge(User user, String badgeName, String description) {

        if (badgeRepository.existsByUserAndBadgeName(user, badgeName)) {
            return null;
        }

        Badge badge = new Badge();
        badge.setUser(user);
        badge.setBadgeName(badgeName);
        badge.setDescription(description);

        return badgeRepository.save(badge);
    }
}









