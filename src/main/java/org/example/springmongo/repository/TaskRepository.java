package org.example.springmongo.repository;

import org.example.springmongo.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface TaskRepository extends MongoRepository<Task, String> {
    @Query(value = "{'name': ?0, 'description': ?1 }")
    Optional<Task> findTaskByTitleAAndDescription(String name, String description);
}
