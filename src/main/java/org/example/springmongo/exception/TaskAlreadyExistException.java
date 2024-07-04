package org.example.springmongo.exception;

public class TaskAlreadyExistException extends RuntimeException {

    public TaskAlreadyExistException()
    {
        super("Task already exist!");
    }

}
