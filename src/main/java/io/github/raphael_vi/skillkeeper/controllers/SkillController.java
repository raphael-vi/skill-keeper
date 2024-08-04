package io.github.raphael_vi.skillkeeper.controllers;

import io.github.raphael_vi.skillkeeper.entities.Skills;
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

    @PostMapping(path="/add")
    public ResponseEntity<String> addNewSkill (@RequestParam(value = "name", required = false) String name){
        if(name == null || name.trim().isEmpty()){
            return ResponseEntity.badRequest().body("Name parameter is null or empty" + name);

        }
        System.out.println("This is being called" + name);
        Skills skill = new Skills();
        skill.setName(name);
        skillsRepository.save(skill);
        return ResponseEntity.ok("ok");
    }

    @GetMapping(path="/all")
    public List<Skills> getAllSkills(){
        return (List<Skills>) skillsRepository.findAll();
    }


}
