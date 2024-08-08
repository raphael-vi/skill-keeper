package io.github.raphael_vi.skillkeeper.repositories;

import io.github.raphael_vi.skillkeeper.entities.Skill;
import io.github.raphael_vi.skillkeeper.entities.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepository extends CrudRepository<Task, Integer> {
    List<Task> findBySkillId(Integer skillId); // Find all skills by field ID
    List<Task> findBySkillName(String skillName);

}
