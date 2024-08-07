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
    public ResponseEntity<List<Field>> getAllFields(){
       List<Field> fields = (List<Field>) fieldsRepository.findAll();
       if(fields.isEmpty()){
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(fields);

    }
    @PatchMapping("/{fieldId}")
    public ResponseEntity<Field> updateField(@PathVariable Integer fieldId, @RequestBody Field fieldUpdates){
        if(!fieldsRepository.existsById(fieldId)){
            return  ResponseEntity.notFound().build();
        }
        return fieldsRepository.findById(fieldId).map(field -> {
            field.setName(fieldUpdates.getName());
            fieldsRepository.save(field);
            return ResponseEntity.ok(field);
        }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{fieldId}")
    public ResponseEntity<?> deleteSkill(@PathVariable Integer fieldId){
        if(!fieldsRepository.existsById(fieldId)){
            return  ResponseEntity.notFound().build();
        }
        return fieldsRepository.findById(fieldId).map(field ->{
            fieldsRepository.delete(field);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

}
