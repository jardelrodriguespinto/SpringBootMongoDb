package org.example.springmongo.controller;

import org.example.springmongo.model.Task;
import org.example.springmongo.repository.TaskRepository;
import org.example.springmongo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class TaskController
{
    private TaskService taskService;

    @Autowired
    public void TaskService(TaskService taskService)
    {
        this.taskService = taskService;
    }

    @GetMapping("/gettaskbyid/{id}")
    public ResponseEntity<Task> findTaskById(String id)
    {
        return null;
    }
    @GetMapping("/getalltasks/")
    public ResponseEntity<Set<Task>> findAllTasks()
    {
        return null;
    }

    @PostMapping("/addTask")
    public ResponseEntity<Task> addTask(Task newTask)
    {

        return null;
    }

    @PutMapping("/modifyTask")
    public ResponseEntity<Task> updateTask(@PathVariable String id, @RequestBody Task newTask)
    {
        return null;
    }
    @DeleteMapping("/deletetaskbyid")
    public ResponseEntity<String> deleteTaskById(@PathVariable String id)
    {
        return null;
    }

    @DeleteMapping("/deletetaskbynameanddescription")
    public ResponseEntity<String> deleteTaskByTitleAndDescription(@RequestBody Task taskToDelete)
    {
        return null;
    }
}
