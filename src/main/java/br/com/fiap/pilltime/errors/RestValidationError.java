package br.com.fiap.pilltime.errors;

public record RestValidationError(
        Integer code,
        String field,
        String message
) {}
