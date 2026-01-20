package com.infosys.carbon.service;

import com.infosys.carbon.entity.Goal;
import com.infosys.carbon.repository.GoalRepository;
import com.infosys.carbon.util.BadgeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalService {

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private BadgeService badgeService;

    // ✅ CREATE GOAL (AUTO BADGE: First Step)
    public Goal createGoal(Goal goal) {

        Goal savedGoal = goalRepository.save(goal);

        // 🥉 First Step Badge
        badgeService.awardIfNotExists(
                savedGoal.getUser(),
                BadgeConstants.FIRST_STEP,
                "Created first carbon reduction goal"
        );

        return savedGoal;
    }

    // ✅ UPDATE GOAL PROGRESS (AUTO BADGES)
    public Goal updateProgress(Long goalId, Double currentCarbon) {

        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new RuntimeException("Goal not found"));

        goal.setCurrentCarbon(currentCarbon);

        double progress = 0.0;
        if (goal.getTargetCarbon() != null && goal.getTargetCarbon() > 0) {
            progress = (currentCarbon / goal.getTargetCarbon()) * 100;
        }

        goal.setProgressPercent(progress);

        // 🥈 Halfway Hero Badge (50%)
        if (progress >= 50) {
            badgeService.awardIfNotExists(
                    goal.getUser(),
                    BadgeConstants.HALFWAY_HERO,
                    "Reached 50% of goal"
            );
        }

        // 🥇 Goal Crusher Badge (100%)
        if (progress >= 100) {
            goal.setStatus("COMPLETED");

            badgeService.awardIfNotExists(
                    goal.getUser(),
                    BadgeConstants.GOAL_CRUSHER,
                    "Completed carbon reduction goal"
            );
        } else {
            goal.setStatus("IN_PROGRESS");
        }

        return goalRepository.save(goal);
    }
}












