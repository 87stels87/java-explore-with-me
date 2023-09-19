package ru.practicum.exception;

public class StatisticsValidationException extends RuntimeException {
    public StatisticsValidationException(String message) {
        super(message);
    }
}