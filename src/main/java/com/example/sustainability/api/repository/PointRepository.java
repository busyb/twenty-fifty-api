package com.example.sustainability.api.repository;

import com.example.sustainability.api.data.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PointRepository extends JpaRepository<UserTask, Long> {
    List<UserTask> findAllByUserId(long userId);
    List<UserTask> findAllByUserIdAndEntryDate(Long userId, LocalDate date);
}
