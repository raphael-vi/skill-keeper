package io.github.raphael_vi.skillkeeper.controllers;

import io.github.raphael_vi.skillkeeper.entities.Skill;
import io.github.raphael_vi.skillkeeper.repositories.FieldsRepository;
import io.github.raphael_vi.skillkeeper.repositories.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/skills")
public class SkillController {
    @Autowired
    private SkillsRepository skillsRepository;
    @Autowired
    private FieldsRepository fieldsRepository;

    @PostMapping
    public ResponseEntity<String> addNewSkill(@PathVariable Integer fieldId, @RequestParam String name) {
        return fieldsRepository.findById(fieldId).map(field -> {
            Skill skill = new Skill();
            skill.setName(name);
            skill.setField(field); // Assuming a Skill has a field property
            skillsRepository.save(skill);
            return ResponseEntity.ok("Skill added to field successfully");
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Skill>> getAllSkills(@PathVariable Long fieldId) {
        List<Skill> skills = skillsRepository.findByFieldId(fieldId);
        if (skills.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(skills);
    }



}
