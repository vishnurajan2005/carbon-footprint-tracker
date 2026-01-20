package com.infosys.carbon.controller;

import com.infosys.carbon.entity.Survey;
import com.infosys.carbon.entity.User;
import com.infosys.carbon.service.SurveyService;
import com.infosys.carbon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/survey")
@CrossOrigin(origins = "*")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private UserRepository userRepository;

    // POST /api/survey/{userId}
    @PostMapping("/{userId}")
    public ResponseEntity<String> submitSurvey(
            @PathVariable Long userId,
            @RequestBody Survey survey) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        survey.setUser(user);   // 🔑 VERY IMPORTANT

        surveyService.saveSurvey(survey);

        return ResponseEntity.ok("Survey submitted successfully");
    }
}





