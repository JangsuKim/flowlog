package com.flowlog.entity;

import com.flowlog.converter.ProjectStatusConverter;
import com.flowlog.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")  // FK 연결
    private Team team;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(nullable = false)
    @Convert(converter = ProjectStatusConverter.class)
    private ProjectStatus status = ProjectStatus.IN_PROGRESS;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @PrePersist
    public void onCreate() {
        createdAt = updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @Transient
    public int getProgress() {
//        if (tasks == null || tasks.isEmpty()) return 0;
//        long completedCount = tasks.stream()
//                .filter(t -> t.getStatus() == TaskStatus.COMPLETED)
//                .count();
//        return (int) ((completedCount * 100.0) / tasks.size());
        return 0;
    }
}
