package com.flowlog.controller;

import com.flowlog.dto.ProjectDto;
import com.flowlog.entity.User;
import com.flowlog.repository.UserRepository;
import com.flowlog.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final UserRepository userRepository;

    // ✅ 전체 프로젝트
    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    // ✅ 팀 ID 기준 조회
    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<ProjectDto>> getProjectsByTeam(@PathVariable Long teamId) {
        return ResponseEntity.ok(projectService.getProjectsByTeamId(teamId));
    }

    // ✅ 프로젝트 생성
    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto dto,
                                                    Authentication authentication) {
        if (authentication == null || authentication.getName() == null) {
            return ResponseEntity.status(401).build();
        }

        String email = authentication.getName();
        User owner = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + email));

        return ResponseEntity.ok(projectService.createProject(dto, owner));
    }

    // ✅ 프로젝트 수정
    @PutMapping("/{id}")
    public ResponseEntity<ProjectDto> updateProject(@PathVariable Long id, @RequestBody ProjectDto dto) {
        return ResponseEntity.ok(projectService.updateProject(id, dto));
    }

    // ✅ 프로젝트 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok().build();
    }
}
