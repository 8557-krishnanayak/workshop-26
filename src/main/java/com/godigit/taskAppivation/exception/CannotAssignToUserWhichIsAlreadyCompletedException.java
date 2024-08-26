package com.godigit.taskAppivation.exception;

public class CannotAssignToUserWhichIsAlreadyCompletedException extends RuntimeException{
    public CannotAssignToUserWhichIsAlreadyCompletedException(String message) {
        super(message);
    }
}
