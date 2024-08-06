package io.github.raphael_vi.skillkeeper.repositories;

import io.github.raphael_vi.skillkeeper.entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillsRepository extends JpaRepository<Skill, Integer> {
    List<Skill> findByFieldId(Long fieldId); // Find all skills by field ID
    List<Skill> findByFieldName(String fieldName);
}
