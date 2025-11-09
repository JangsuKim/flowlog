package com.flowlog.converter;

import com.flowlog.enums.ProjectStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false) // 안전하게 개별 필드에만 명시 적용
public class ProjectStatusConverter implements AttributeConverter<ProjectStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ProjectStatus attribute) {
        return attribute == null ? null : attribute.getCode();
    }

    @Override
    public ProjectStatus convertToEntityAttribute(Integer dbData) {
        return dbData == null ? null : ProjectStatus.fromCode(dbData);
    }
}
