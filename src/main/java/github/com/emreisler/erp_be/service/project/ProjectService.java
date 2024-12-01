package github.com.emreisler.erp_be.service.project;

import github.com.emreisler.erp_be.dto.ProjectDto;

import java.util.List;

public interface ProjectService {
    List<ProjectDto> getAll();

    ProjectDto getByCode(String code);

    ProjectDto create(ProjectDto projectDto);

    ProjectDto update(String code, ProjectDto projectDto);

    void deleteByCode(String code);
}
