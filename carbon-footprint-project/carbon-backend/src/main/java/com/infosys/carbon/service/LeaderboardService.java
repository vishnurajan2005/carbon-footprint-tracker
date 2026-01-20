package com.infosys.carbon.service;

import com.infosys.carbon.dto.LeaderboardDTO;
import com.infosys.carbon.repository.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderboardService {

    @Autowired
    private BadgeRepository badgeRepository;

    public List<LeaderboardDTO> getLeaderboard() {
        return badgeRepository.getLeaderboard();
    }
}

