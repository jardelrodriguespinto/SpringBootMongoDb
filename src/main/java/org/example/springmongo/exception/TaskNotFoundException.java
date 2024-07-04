package org.example.springmongo.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException()
    {
        super("Task not fond! ");
    }
}
