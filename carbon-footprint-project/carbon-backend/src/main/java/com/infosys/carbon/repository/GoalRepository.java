package com.infosys.carbon.repository;

import com.infosys.carbon.entity.Goal;
import com.infosys.carbon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {

    Optional<Goal> findByUser(User user);

    List<Goal> findAllByOrderByProgressPercentDesc();
}









