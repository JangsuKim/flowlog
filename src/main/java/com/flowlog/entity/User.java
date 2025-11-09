package com.flowlog.entity;

import com.flowlog.converter.RoleTypeConverter;
import com.flowlog.enums.RoleType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 100)
    private String name;

    // ✅ team_id로 변경
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")  // FK 연결
    private Team team;

    @Column(length = 255)
    private String profileImage;

    @Convert(converter = RoleTypeConverter.class)
    @Column(nullable = false)
    private RoleType role = RoleType.MEMBER;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
