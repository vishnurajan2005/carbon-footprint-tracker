package com.infosys.carbon.controller;

import com.infosys.carbon.entity.Goal;
import com.infosys.carbon.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/goals")
@CrossOrigin(origins = "*")
public class GoalController {

    @Autowired
    private GoalService goalService;

    // ✅ Create Goal
    @PostMapping
    public ResponseEntity<Goal> createGoal(@RequestBody Goal goal) {
        return ResponseEntity.ok(goalService.createGoal(goal));
    }

    // ✅ Update Progress
    @PutMapping("/{id}/progress")
    public ResponseEntity<Goal> updateProgress(
            @PathVariable Long id,
            @RequestParam Double currentCarbon) {

        return ResponseEntity.ok(
                goalService.updateProgress(id, currentCarbon)
        );
    }
}







