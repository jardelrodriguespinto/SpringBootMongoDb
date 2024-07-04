package org.example.springmongo.repository;

import org.example.springmongo.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
    @Query(value = "{'name': ?0, 'description': ?0, }")
    Optional<Task> findTaskByTitleAAndDescription(String name, String description);
}
