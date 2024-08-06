package io.github.raphael_vi.skillkeeper.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.config.Task;

public interface TasksRepository extends CrudRepository<Task, Integer> {

}
