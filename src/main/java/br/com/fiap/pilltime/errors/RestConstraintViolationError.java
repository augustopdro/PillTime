package br.com.fiap.pilltime.errors;

public record RestConstraintViolationError(
        int code,
        Object field,
        String message
) {}
