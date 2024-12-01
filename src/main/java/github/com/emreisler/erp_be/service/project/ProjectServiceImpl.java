package github.com.emreisler.erp_be.service.project;

import github.com.emreisler.erp_be.converters.ProjectConverter;
import github.com.emreisler.erp_be.dto.ProjectDto;
import github.com.emreisler.erp_be.entity.Project;
import github.com.emreisler.erp_be.exception.ProjectNotFoundException;
import github.com.emreisler.erp_be.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectDto> getAll() {
        return projectRepository.findAll().stream()
                .map(ProjectConverter::toDto)
                .collect(Collectors.toList());
    }

    public ProjectDto getByCode(String code) {
        return projectRepository.findByCode(code)
                .map(ProjectConverter::toDto)
                .orElseThrow(ProjectNotFoundException::new);
    }

    public ProjectDto create(ProjectDto projectDto) {
        Project project = new Project();
        project.setUuid(UUID.randomUUID());
        project.setCode(projectDto.getCode());
        project.setName(projectDto.getName());

        Project savedProject = projectRepository.save(project);
        return new ProjectDto(savedProject.getUuid(), savedProject.getCode(), savedProject.getName());
    }

    public ProjectDto update(String code, ProjectDto projectDto) {
        Project project = projectRepository.findByCode(code)
                .orElseThrow(ProjectNotFoundException::new);

        project.setCode(projectDto.getCode());
        project.setName(projectDto.getName());

        Project updatedProject = projectRepository.save(project);
        return new ProjectDto(updatedProject.getUuid(), updatedProject.getCode(), updatedProject.getName());
    }

    @Override
    @Transactional
    public void deleteByCode(String code) {
        if (!projectRepository.existsByCode(code)) {
            throw new ProjectNotFoundException();
        }
        projectRepository.deleteByCode(code);
    }
}
