package com.flowlog.dto;

import com.flowlog.entity.Project;
import com.flowlog.entity.Team;
import com.flowlog.enums.ProjectStatus;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ProjectDto(
        Long id,
        String name,
        Long teamId,
        String teamName,
        String description,
        LocalDate dueDate,
        ProjectStatus status,
        String ownerName
) {
    public static ProjectDto fromEntity(Project p) {
        return ProjectDto.builder()
                .id(p.getId())
                .name(p.getName())
                .teamId(p.getTeam() != null ? p.getTeam().getId() : null)
                .teamName(p.getTeam() != null ? p.getTeam().getName() : null)
                .description(p.getDescription())
                .dueDate(p.getDueDate())
                .status(p.getStatus())
                .ownerName(p.getOwner() != null ? p.getOwner().getName() : null)
                .build();
    }
}
