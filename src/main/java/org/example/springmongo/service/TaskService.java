package org.example.springmongo.service;

import org.example.springmongo.model.Task;
import org.example.springmongo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public void TaskRepository(TaskRepository taskRepository)
    {
        this.taskRepository = taskRepository;
    }

    public Task findTaskById(String id)
    {
        return taskRepository.findById(id).orElse(null);
    }

    public Set<Task> findAllTasks()
    {
        return new HashSet<>(taskRepository.findAll());
    }

    public Task addTask(Task newTask)
    {
        Optional<Task> task = taskRepository.findTaskByTitleAAndDescription(
                newTask.getTitle(),newTask.getDescription());

        if (! task.isPresent())
        {
            taskRepository.save(newTask);
        }

        return null;
    }

    public Task updateTask(String id, Task newTask)
    {
        Optional<Task> task = taskRepository.findById(id);

        if (task.isPresent())
        {
            task.get().setTitle(newTask.getTitle());
            task.get().setDescription(newTask.getDescription());
            task.get().setDueDate(newTask.getDueDate());

            taskRepository.save(task.get());
        }

        return task.get();
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
