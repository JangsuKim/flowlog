package com.flowlog.controller;

import com.flowlog.dto.LoginRequest;
import com.flowlog.dto.RegisterRequest;
import com.flowlog.entity.Team;
import com.flowlog.enums.RoleType;
import com.flowlog.repository.TeamRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) // 🔸 JWT 필터 비활성화
@Transactional
class AuthControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private TeamRepository teamRepository;

    private Team testTeam;

    @BeforeEach
    void setup() {
        // ✅ 테스트용 팀 생성 또는 재활용
        testTeam = teamRepository.findByName("Backend")
                .orElseGet(() -> teamRepository.save(Team.builder()
                        .name("Backend")
                        .build()));
    }

    /**
     * ✅ 회원가입 테스트 (팀 정보 포함)
     */
    @Test
    void registerUser() throws Exception {
        String randomEmail = "mockuser_" + java.util.UUID.randomUUID().toString().substring(0, 8) + "@example.com";

        RegisterRequest request = new RegisterRequest();
        request.setEmail(randomEmail);
        request.setPassword("password123");
        request.setName("Mock User");
        request.setTeamId(testTeam.getId());  // ✅ 실제 존재하는 팀 ID
        request.setRole(RoleType.MEMBER);     // ✅ 기본값 MEMBER

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(randomEmail))
                .andExpect(jsonPath("$.role").value("MEMBER"))
                .andExpect(jsonPath("$.team.id").value(testTeam.getId().intValue()))
                .andExpect(jsonPath("$.team.name").value("Backend"));
    }

    /**
     * ✅ 로그인 테스트
     * (회원가입 이후 실제 로그인 테스트용)
     */
    @Test
    void loginUser() throws Exception {
        // 1️⃣ 회원가입 먼저 수행
        String email = "mocklogin_" + java.util.UUID.randomUUID().toString().substring(0, 8) + "@example.com";
        RegisterRequest register = new RegisterRequest();
        register.setEmail(email);
        register.setPassword("password123");
        register.setName("LoginUser");
        register.setTeamId(testTeam.getId());
        register.setRole(RoleType.MEMBER);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(register)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.team.id").value(testTeam.getId().intValue()))
                .andExpect(jsonPath("$.team.name").value("Backend"));

        // 2️⃣ 로그인 테스트
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword("password123");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists())
                .andExpect(jsonPath("$.email").value(email))
                .andExpect(jsonPath("$.userId").exists())
                .andExpect(jsonPath("$.team.id").value(testTeam.getId().intValue()))
                .andExpect(jsonPath("$.role").value("MEMBER"));
    }
}
