package com.flowlog.controller;

import com.flowlog.entity.Team;
import com.flowlog.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamRepository teamRepository;

    // ✅ 모든 팀 목록 조회
    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        return ResponseEntity.ok(teams);
    }

    // ✅ 단일 팀 조회 (선택)
    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        return teamRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ 새 팀 생성 (관리자용)
    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        Team saved = teamRepository.save(team);
        return ResponseEntity.ok(saved);
    }
}
