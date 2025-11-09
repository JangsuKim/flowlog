package com.flowlog.service;

import com.flowlog.dto.ProjectDto;
import com.flowlog.entity.Project;
import com.flowlog.entity.Team;
import com.flowlog.entity.User;
import com.flowlog.enums.ProjectStatus;
import com.flowlog.repository.ProjectRepository;
import com.flowlog.repository.TeamRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final TeamRepository teamRepository;

    // ✅ 전체 프로젝트
    public List<ProjectDto> getAllProjects() {
        return projectRepository.findAllActive()
                .stream()
                .map(ProjectDto::fromEntity)
                .collect(Collectors.toList());
    }

    // ✅ 팀 ID 기준 프로젝트 조회
    public List<ProjectDto> getProjectsByTeamId(Long teamId) {
        return projectRepository.findByTeamId(teamId)
                .stream()
                .map(ProjectDto::fromEntity)
                .collect(Collectors.toList());
    }

    // ✅ 프로젝트 생성
    public ProjectDto createProject(ProjectDto dto, User owner) {
        Team team = teamRepository.findById(dto.teamId())
                .orElseThrow(() -> new IllegalArgumentException("팀을 찾을 수 없습니다."));

        Project p = Project.builder()
                .name(dto.name())
                .description(dto.description())
                .dueDate(dto.dueDate() != null ? dto.dueDate() : LocalDate.now().plusMonths(1))
                .status(dto.status() != null ? dto.status() : ProjectStatus.IN_PROGRESS)
                .owner(owner)
                .team(team)
                .build();

        return ProjectDto.fromEntity(projectRepository.save(p));
    }

    // ✅ 프로젝트 수정
    public ProjectDto updateProject(Long id, ProjectDto dto) {
        Project p = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        if (dto.name() != null) p.setName(dto.name());
        if (dto.teamId() != null) {
            Team team = teamRepository.findById(dto.teamId())
                    .orElseThrow(() -> new IllegalArgumentException("Team not found"));
            p.setTeam(team);
        }
        if (dto.description() != null) p.setDescription(dto.description());
        if (dto.dueDate() != null) p.setDueDate(dto.dueDate());
        if (dto.status() != null) p.setStatus(dto.status());

        return ProjectDto.fromEntity(projectRepository.save(p));
    }

    // ✅ Soft Delete
    public void deleteProject(Long id) {
        Project p = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));
        p.setDeletedAt(LocalDateTime.now());
        projectRepository.save(p);
    }
}
