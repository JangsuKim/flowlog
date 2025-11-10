package com.flowlog.controller;

import com.flowlog.dto.ProjectDto;
import com.flowlog.entity.User;
import com.flowlog.enums.RoleType;
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

    // ✅ 프로젝트 조회
    @GetMapping
    public ResponseEntity<List<ProjectDto>> getProjects(
            @RequestParam(required = false) Long teamId,
            Authentication authentication
    ) {
        // 로그인 사용자 조회
        if (authentication == null || authentication.getName() == null) {
            return ResponseEntity.status(401).build();
        }
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + authentication.getName()));

        boolean isLeader = user.getRole() == RoleType.LEADER;

        // 👇 멤버는 항상 자신의 팀으로 강제 필터링 (클라이언트 신뢰 X)
        Long effectiveTeamId = isLeader ? teamId : (user.getTeam() != null ? user.getTeam().getId() : null);

        List<ProjectDto> result = (effectiveTeamId != null)
                ? projectService.getProjectsByTeamId(effectiveTeamId)
                : projectService.getAllProjects();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
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
