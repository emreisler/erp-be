package github.com.emreisler.erp_be.converters;

import github.com.emreisler.erp_be.application.dto.ProjectDto;
import github.com.emreisler.erp_be.persistence.entity.Project;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectConverter {

    public static ProjectDto toDto(Project project) {
        if (project == null) {
            return null;
        }

        return new ProjectDto(
                project.getUuid(),
                project.getCode(),
                project.getName()
        );
    }

    public static Project toEntity(ProjectDto projectDto) {
        if (projectDto == null) {
            return null;
        }

        Project project = new Project();
        project.setUuid(projectDto.getUuid());
        project.setCode(projectDto.getCode());
        project.setName(projectDto.getName());
        return project;
    }

    public static List<ProjectDto> toDto(List<Project> projects) {
        if (projects == null) {
            return null;
        }
        return projects.stream().map(ProjectConverter::toDto).collect(Collectors.toList());
    }

    public static List<Project> toEntity(List<ProjectDto> projectDtos) {
        if (projectDtos == null) {
            return null;
        }
        return projectDtos.stream().map(ProjectConverter::toEntity).collect(Collectors.toList());
    }
}
