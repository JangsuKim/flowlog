package com.flowlog.repository;

import com.flowlog.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findByIdAndDeletedAtIsNull(Long id);

    // ✅ Soft Delete 제외 전체 조회
    @Query("SELECT p FROM Project p WHERE p.deletedAt IS NULL ORDER BY p.createdAt DESC")
    List<Project> findAllActive();

    // ✅ 팀 ID 기준 조회 (teamName → team.id 변경)
    @Query("SELECT p FROM Project p WHERE p.team.id = :teamId AND p.deletedAt IS NULL ORDER BY p.createdAt DESC")
    List<Project> findByTeamId(Long teamId);
}
