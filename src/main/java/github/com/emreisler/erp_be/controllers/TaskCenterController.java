package github.com.emreisler.erp_be.controllers;

import github.com.emreisler.erp_be.dto.TaskCenterDto;
import github.com.emreisler.erp_be.service.taskCenter.TaskCenterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "http://localhost:3000", originPatterns = {"*"})
public class TaskCenterController {

    private final TaskCenterService taskCenterService;

    TaskCenterController(TaskCenterService taskCenterService) {
        this.taskCenterService = taskCenterService;
    }

    @GetMapping("/task-center")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<TaskCenterDto>> GetTaskCenters() throws Exception {
        return ResponseEntity.ok(this.taskCenterService.GetAll());
    }

    @GetMapping("/task-center/{tcNo}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<TaskCenterDto> GetTaskCenterByTcNo(@PathVariable int tcNo) throws Exception {
        return ResponseEntity.ok(this.taskCenterService.GetByNumber(tcNo));
    }

    @PostMapping("/task-center")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<TaskCenterDto> AddTaskCenter(@RequestBody TaskCenterDto taskCenterDto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.taskCenterService.Create(taskCenterDto));
    }

    @PutMapping("/task-center/{tcNo}")
    @CrossOrigin(origins = "http://localhost:3000")
    public TaskCenterDto UpdateTaskCenter(@PathVariable int tcNo, @RequestBody TaskCenterDto taskCenterDto) throws Exception {
        return this.taskCenterService.Update(tcNo, taskCenterDto);
    }

    @DeleteMapping("/task-center/{tcNo}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> DeleteTaskCenter(@PathVariable int tcNo) throws Exception {
        taskCenterService.Delete(tcNo);
        return ResponseEntity.ok("Task center deleted");
    }

}
