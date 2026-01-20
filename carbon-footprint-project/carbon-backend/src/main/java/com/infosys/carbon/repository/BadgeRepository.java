package com.infosys.carbon.repository;

import com.infosys.carbon.entity.Badge;
import com.infosys.carbon.entity.User;
import com.infosys.carbon.dto.LeaderboardDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {

    // ✅ Used in Task 4 & 5 (fetch user badges)
    List<Badge> findByUser(User user);

    // ✅ Used in Task 5 (prevent duplicate badges)
    boolean existsByUserAndBadgeName(User user, String badgeName);

    // ✅ Used in Task 6 (Leaderboard)
    @Query("""
        SELECT new com.infosys.carbon.dto.LeaderboardDTO(
            u.id, u.name, COUNT(b)
        )
        FROM Badge b
        JOIN b.user u
        GROUP BY u.id, u.name
        ORDER BY COUNT(b) DESC
    """)
    List<LeaderboardDTO> getLeaderboard();
}





