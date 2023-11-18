package br.com.fiap.pilltime.dtos;

import br.com.fiap.pilltime.models.Lembrete;

import java.util.List;

public record PaginationResponseDTO(
    List<Lembrete> content,
    int number,
    long totalElements,
    int totalPages,
    boolean first,
    boolean last
) {}
