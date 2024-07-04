package org.example.springmongo.controller;
import org.apache.logging.log4j.Logger;
import org.example.springmongo.exception.GenericException;
import org.example.springmongo.exception.TaskAlreadyExistException;
import org.example.springmongo.exception.TaskNotFoundException;
import org.example.springmongo.model.Task;
import org.example.springmongo.service.TaskService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class TaskController
{
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory(TaskController.class);
    private TaskService taskService;

    @Autowired
    public void taskService(TaskService taskService)
    {
        this.taskService = taskService;
    }

    @GetMapping("/gettaskbyid/{id}")
    public ResponseEntity<Task> findTaskById(@PathVariable String id)
    {
        try
        {
            Task task = taskService.findTaskById(id);

            return ResponseEntity.ok(task);
        }
        catch (TaskNotFoundException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            throw new GenericException();
        }
    }
    @GetMapping("/getalltasks")
    public ResponseEntity<Set<Task>> findAllTasks()
    {
        try
        {
            Set<Task> tasks = taskService.findAllTasks();

            return ResponseEntity.ok(tasks);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            throw new GenericException();
        }
    }

    @PostMapping("/addTask")
    public ResponseEntity<Task> addTask(@RequestBody Task newTask)
    {
        try
        {
            //Criar dto

            Task task = taskService.addTask(newTask);

            return ResponseEntity.ok(task);
        }
        catch (TaskAlreadyExistException e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            throw new GenericException();
        }
    }

    @PutMapping("/modifyTask/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable String id, @RequestBody Task newTask)
    {
        try
        {
            Task task = taskService.updateTask(id, newTask);

            return ResponseEntity.ok(task);
        }
        catch (TaskNotFoundException e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            throw new GenericException();
        }
    }
    @DeleteMapping("/deletetaskbyid/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable String id)
    {
        try
        {
            taskService.deleteTaskById(id);

            return ResponseEntity.ok("task deleted successfully");
        }
        catch (TaskNotFoundException e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            throw new GenericException();
        }
    }

    @DeleteMapping("/deletetaskbynameanddescription")
    public ResponseEntity<String> deleteTaskByTitleAndDescription(@RequestBody Task taskToDelete)
    {
        try
        {
            taskService.deleteTaskByTitleAndDescription(taskToDelete);

            return ResponseEntity.ok("task deleted successfully");
        }
        catch (TaskNotFoundException e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            throw new GenericException();
        }
    }
}
