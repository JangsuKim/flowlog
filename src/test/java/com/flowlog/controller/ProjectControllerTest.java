package com.flowlog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flowlog.entity.Project;
import com.flowlog.entity.Team;
import com.flowlog.entity.User;
import com.flowlog.enums.ProjectStatus;
import com.flowlog.repository.ProjectRepository;
import com.flowlog.repository.TeamRepository;
import com.flowlog.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = true)
@Import(com.flowlog.config.TestSecurityConfig.class)
@Transactional
class ProjectControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private ProjectRepository projectRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private TeamRepository teamRepository;

    private User testUser;
    private Team testTeam;

    @BeforeEach
    void setup() {
        // ✅ 테스트용 유저 (DB에 존재하는 유저 사용)
        testUser = userRepository.findByEmail("project_test@flowlog.com")
                .orElseThrow(() -> new RuntimeException("테스트 유저(project_test@flowlog.com)가 없습니다."));

        // ✅ 테스트용 팀
        testTeam = teamRepository.findByName("Backend")
                .orElseGet(() -> teamRepository.save(Team.builder()
                        .name("Backend")
                        .build()));
    }

    /**
     * ✅ 프로젝트 생성 테스트
     */
    @Test
    @WithMockUser(username = "project_test@flowlog.com")
    void createProject() throws Exception {
        String requestJson = """
            {
              "name": "FlowLog API Test Project",
              "teamId": %d,
              "description": "SpringBoot 통합 테스트 프로젝트입니다.",
              "dueDate": "2025-12-31",
              "status": "IN_PROGRESS"
            }
        """.formatted(testTeam.getId());

        mockMvc.perform(post("/api/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("FlowLog API Test Project"))
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"));
    }

    /**
     * ✅ 프로젝트 목록 조회 테스트
     */
    @Test
    void getProjects() throws Exception {
        mockMvc.perform(get("/api/projects")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    /**
     * ✅ 프로젝트 수정 테스트
     */
    @Test
    void updateProject() throws Exception {
        // 1️⃣ 테스트용 프로젝트 저장
        var saved = projectRepository.save(Project.builder()
                .name("Before Update")
                .team(testTeam)
                .description("테스트용 프로젝트")
                .dueDate(LocalDate.now().plusDays(5))
                .status(ProjectStatus.IN_PROGRESS)
                .owner(testUser)
                .build());

        // 2️⃣ 수정 요청 JSON
        String updateJson = """
            {
              "name": "After Update",
              "teamId": %d,
              "description": "수정된 내용입니다.",
              "dueDate": "2025-12-31",
              "status": "COMPLETED"
            }
        """.formatted(testTeam.getId());

        mockMvc.perform(put("/api/projects/" + saved.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("After Update"))
                .andExpect(jsonPath("$.status").value("COMPLETED"));
    }

    /**
     * ✅ 프로젝트 삭제 테스트 (Soft Delete)
     */
    @Test
    void deleteProject() throws Exception {
        var saved = projectRepository.save(Project.builder()
                .name("삭제 테스트 프로젝트")
                .team(testTeam)
                .description("삭제 테스트용 프로젝트")
                .dueDate(LocalDate.now().plusDays(3))
                .status(ProjectStatus.PENDING)
                .owner(testUser)
                .build());

        mockMvc.perform(delete("/api/projects/" + saved.getId()))
                .andExpect(status().isOk());
    }
}
