package io.github.raphael_vi.skillkeeper.controllers;

import io.github.raphael_vi.skillkeeper.entities.Task;
import io.github.raphael_vi.skillkeeper.repositories.TasksRepository;
import io.github.raphael_vi.skillkeeper.repositories.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/fields/{fieldId}/skills/{skillId}/tasks")
public class TaskController {
    @Autowired
    private TasksRepository tasksRepository;

    @Autowired
    private SkillsRepository skillsRepository;

    @PostMapping
    public ResponseEntity<?> addTask(@PathVariable Integer fieldId, @PathVariable Integer skillId, @RequestBody Task task) {
        return skillsRepository.findById(skillId).map(skill -> {
            task.setSkill(skill);
            tasksRepository.save(task);
            return ResponseEntity.ok(task);
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks(@PathVariable Integer fieldId, @PathVariable Integer skillId) {
        List<Task> tasks = tasksRepository.findBySkillId(skillId);
        if (tasks.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tasks);
    }

    @PatchMapping("/{taskId}")
    public ResponseEntity<?> updateTask(@PathVariable Integer skillId, @PathVariable Integer taskId, @RequestBody Task taskUpdates) {
        return tasksRepository.findById(taskId).map(task -> {
            task.setName(taskUpdates.getName());
            tasksRepository.save(task);
            return ResponseEntity.ok(task);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Integer skillId, @PathVariable Integer taskId) {
        if (!skillsRepository.existsById(skillId)) {
            return ResponseEntity.notFound().build();
        }
        tasksRepository.deleteById(taskId);
        return ResponseEntity.ok().build();
    }
}
