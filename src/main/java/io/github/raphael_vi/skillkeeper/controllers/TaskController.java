package io.github.raphael_vi.skillkeeper.controllers;

import io.github.raphael_vi.skillkeeper.entities.Task;
import io.github.raphael_vi.skillkeeper.repositories.FieldsRepository;
import io.github.raphael_vi.skillkeeper.repositories.SkillsRepository;
import io.github.raphael_vi.skillkeeper.repositories.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* I have a lot of thoughts on how tasks are going to be managed.
*  are they going to add a fixed amount of points? Are the points going to
*  be set by the user? Or is it by time spent? IDK yet. But here is the thing
*  when i'm better at this, i'll implement the choice for the user.
*  for now, let's give ourselves a mvp.
*  */

@RestController
@RequestMapping(path="/fields/{fieldId}/skills/{skillId}/tasks")
public class TaskController {
    @Autowired
    private TasksRepository taskRepository;

    @Autowired
    private SkillsRepository skillsRepository;
    @Autowired
    private FieldsRepository fieldsRepository;



    // POST to add a new task
    @PostMapping
    public ResponseEntity<?> addTask(@PathVariable Integer fieldId, @PathVariable Integer skillId, @RequestBody Task task) {
       return skillsRepository.findById(skillId).map(skill -> {
           task.setSkill(skill);
           taskRepository.save(task);
           return ResponseEntity.ok(task);

       }).orElse(ResponseEntity.notFound().build());
    }

    // GET to retrieve all tasks for a skill
    @GetMapping
    public ResponseEntity<List<Task>> getTasks(@PathVariable Integer fieldId, @PathVariable Integer skillId) {
        // Implementation to retrieve all tasks for the specified skill
        // Return tasks or not found status
    }

    // Additional methods for update and delete
}
