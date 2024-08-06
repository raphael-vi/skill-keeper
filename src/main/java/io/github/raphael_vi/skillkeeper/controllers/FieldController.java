package io.github.raphael_vi.skillkeeper.controllers;

import io.github.raphael_vi.skillkeeper.entities.Field;
import io.github.raphael_vi.skillkeeper.repositories.FieldsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/fields")
public class FieldController {
    @Autowired
    private FieldsRepository fieldsRepository;

    @PostMapping
    public ResponseEntity<String> addNewField(@RequestParam(value = "name", required = true) String name){
        if(name == null || name.trim().isEmpty()){
            return ResponseEntity.badRequest().body("Insert a valid name" + name);
        }
        Field field = new Field();
        field.setName(name);
        fieldsRepository.save(field);
        return ResponseEntity.ok("ok");
    }

    @GetMapping
    public List<Field> getAllFields(){
       return (List<Field>) fieldsRepository.findAll();
    }

}
