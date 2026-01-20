package com.infosys.carbon.service;

import com.infosys.carbon.entity.*;
import com.infosys.carbon.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private CarbonLogRepository carbonLogRepository;

    public void saveSurvey(Survey survey) {

        surveyRepository.save(survey);
        User user = survey.getUser();

        Goal goal = goalRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Goal not found"));

        double carbon = survey.getEnergyUsage();

        // Carbon log
        CarbonLog log = new CarbonLog();
        log.setUser(user);
        log.setCarbonAmount(carbon);
        carbonLogRepository.save(log);

        // Goal update
        double newCarbon = goal.getCurrentCarbon() + carbon;
        goal.setCurrentCarbon(newCarbon);

        double progress = (newCarbon / goal.getTargetCarbon()) * 100;
        goal.setProgressPercent(Math.min(progress, 100));

        if (progress >= 100) {
            goal.setStatus("COMPLETED");
        } else {
            goal.setStatus("IN_PROGRESS");
        }

        goalRepository.save(goal);

        // 🚨 ALERT (Milestone 4)
        if (carbon > 20) {
            System.out.println(
                "⚠ ALERT: High carbon emission detected for user " + user.getId()
            );
        }
    }
}










