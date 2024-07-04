package org.example.springmongo.service;

import org.example.springmongo.exception.TaskAlreadyExistException;
import org.example.springmongo.exception.TaskNotFoundException;
import org.example.springmongo.model.Task;
import org.example.springmongo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public void taskRepository(TaskRepository taskRepository)
    {
        this.taskRepository = taskRepository;
    }

    public Task findTaskById(String id)
    {
        return taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
    }

    public Set<Task> findAllTasks()
    {
        return new HashSet<>(taskRepository.findAll());
    }

    public Task addTask(Task newTask)
    {
        Optional<Task> task = taskRepository.findTaskByTitleAAndDescription(
                newTask.getTitle(),newTask.getDescription());

        if (task.isPresent())
            throw new TaskAlreadyExistException();

        return taskRepository.save(newTask);
    }

    public Task updateTask(String id, Task newTask)
    {
        Optional<Task> foundTask = taskRepository.findById(id);

        Consumer<Task> modifyAndSave = task ->
        {
            task.setTitle(newTask.getTitle());
            task.setDescription(newTask.getDescription());
            task.setDueDate(newTask.getDueDate());
            taskRepository.save(task);
        };

        foundTask.ifPresentOrElse(modifyAndSave, TaskNotFoundException::new);

        return foundTask.get();
    }

    public void deleteTaskById(String id)
    {
        Optional<Task> task = taskRepository.findById(id);

        task.ifPresent(action -> taskRepository.delete(task.get()));
    }

    public void deleteTaskByTitleAndDescription(Task taskToDelete)
    {
        Optional<Task> task = taskRepository.findTaskByTitleAAndDescription(taskToDelete.getTitle(), taskToDelete.getTitle());

        task.ifPresent(action -> taskRepository.delete(task.get()));
    }
}