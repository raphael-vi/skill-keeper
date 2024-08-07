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
    public ResponseEntity<List<Skill>> getAllSkills(@PathVariable Integer fieldId) {
        List<Skill> skills = skillsRepository.findByFieldId(fieldId);
        if (skills.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(skills);
    }

    @PatchMapping("/{skillId}")
    public ResponseEntity<Skill> updateSkill(@PathVariable Integer fieldId, @PathVariable Integer skillId, @RequestBody Skill skillUpdates) {
        if (!fieldsRepository.existsById(fieldId)){
            return ResponseEntity.notFound().build();
        }
        return skillsRepository.findById(skillId).map(skill -> {
            skill.setName(skillUpdates.getName()); //Note to self: this is Spring magic from @RequestBody
            skillsRepository.save(skill);
            return ResponseEntity.ok(skill);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{skillId}")
    public ResponseEntity<?> deleteSkill(@PathVariable Integer fieldId, @PathVariable Integer skillId){
        if (!fieldsRepository.existsById(fieldId)){
            return ResponseEntity.notFound().build();
        }
        return skillsRepository.findById(skillId).map(skill -> {
            skillsRepository.delete(skill);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
