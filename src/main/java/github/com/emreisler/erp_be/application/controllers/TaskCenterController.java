package github.com.emreisler.erp_be.application.controllers;

import github.com.emreisler.erp_be.application.dto.TaskCenterDto;
import github.com.emreisler.erp_be.domain.service.taskCenter.TaskCenterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1")
public class TaskCenterController {

    private final TaskCenterService taskCenterService;

    TaskCenterController(TaskCenterService taskCenterService) {
        this.taskCenterService = taskCenterService;
    }

    @GetMapping("/task-center")
    public ResponseEntity<List<TaskCenterDto>> GetTaskCenters() throws Exception {
        return ResponseEntity.ok(this.taskCenterService.GetAll());
    }

    @GetMapping("/task-center/{tcNo}")
    public ResponseEntity<TaskCenterDto> GetTaskCenterByTcNo(@PathVariable int tcNo) throws Exception {
        return ResponseEntity.ok(this.taskCenterService.GetByNumber(tcNo));
    }

    @PostMapping("/task-center")
    public ResponseEntity<TaskCenterDto> AddTaskCenter(@RequestBody TaskCenterDto taskCenterDto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.taskCenterService.Create(taskCenterDto));
    }

    @PutMapping("/task-center/{tcNo}")
    public TaskCenterDto UpdateTaskCenter(@PathVariable int tcNo, @RequestBody TaskCenterDto taskCenterDto) throws Exception {
        return this.taskCenterService.Update(tcNo, taskCenterDto);
    }

    @DeleteMapping("/task-center/{tcNo}")
    public ResponseEntity<String> DeleteTaskCenter(@PathVariable int tcNo) throws Exception {
        taskCenterService.Delete(tcNo);
        return ResponseEntity.ok("Task center deleted");
    }

}
