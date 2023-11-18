package br.com.fiap.pilltime.dtos;

import br.com.fiap.pilltime.models.Lembrete;

import java.util.List;

public record HistoricoDTO(List<Lembrete> lembretes) {}

