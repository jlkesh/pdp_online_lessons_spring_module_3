package uz.pdp.springadvanced.post;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;


    @GetMapping
    public ResponseEntity<List<Task>> getAll() {
        log.info("Request For All Tasks");
        return ResponseEntity.ok(taskRepository.getAll());
    }


}
