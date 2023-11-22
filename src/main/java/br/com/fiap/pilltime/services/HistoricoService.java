package br.com.fiap.pilltime.services;

import br.com.fiap.pilltime.dtos.PaginationResponseDTO;
import br.com.fiap.pilltime.repositories.LembreteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HistoricoService {

    Logger log = LoggerFactory.getLogger(HistoricoService.class);
    private LembreteRepository lembreteRepository;

    @Autowired
    public HistoricoService(LembreteRepository lembreteRepository) {
        this.lembreteRepository = lembreteRepository;
    }

    public PaginationResponseDTO recuperarHistorico(long userId, Pageable pageable) {
        log.info("Buscando historico de lembretes do usuário: " + userId);

        var registros = lembreteRepository.getAllRegisters(userId, pageable);

        var responsePersonalizado = new PaginationResponseDTO(
            registros.getContent(),
            registros.getNumber(),
            registros.getTotalElements(),
            registros.getTotalPages(),
            registros.isFirst(),
            registros.isLast()
        );

        return responsePersonalizado;
    }
}
