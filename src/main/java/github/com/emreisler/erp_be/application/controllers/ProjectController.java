package github.com.emreisler.erp_be.application.controllers;

import github.com.emreisler.erp_be.application.dto.ProjectDto;
import github.com.emreisler.erp_be.domain.service.project.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAll());
    }

    @GetMapping("/{code}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ProjectDto> getProjectByCode(@PathVariable String code) {
        return ResponseEntity.ok(projectService.getByCode(code));
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto) {
        ProjectDto createdProject = projectService.create(projectDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    @PutMapping("/{code}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ProjectDto> updateProject(@PathVariable String code, @RequestBody ProjectDto projectDto) {
        return ResponseEntity.ok(projectService.update(code, projectDto));
    }

    @DeleteMapping("/{code}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> deleteProject(@PathVariable String code) {
        projectService.deleteByCode(code);
        return ResponseEntity.ok("Project deleted successfully");
    }
}
