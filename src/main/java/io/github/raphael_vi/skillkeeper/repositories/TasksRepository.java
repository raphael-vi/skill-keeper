package io.github.raphael_vi.skillkeeper.repositories;

import io.github.raphael_vi.skillkeeper.entities.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepository extends CrudRepository<Task, Integer> {

}
