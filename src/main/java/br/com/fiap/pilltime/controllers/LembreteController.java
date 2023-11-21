package br.com.fiap.pilltime.controllers;

import br.com.fiap.pilltime.dtos.*;
import br.com.fiap.pilltime.exceptions.RestBadRequestException;
import br.com.fiap.pilltime.models.Lembrete;
import br.com.fiap.pilltime.services.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/sono")
@SecurityRequirement(name = "bearer-key")
public class LembreteController {
	private LembreteService lembreteService;
    private HistoricoService historicoService;
    private Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    public LembreteController(LembreteService lembreteService, HistoricoService historicoService) {
        this.lembreteService = lembreteService;
        this.historicoService = historicoService;
    }

    @PostMapping("{userId}/registrar")
    public ResponseEntity<EntityModel<Lembrete>> registrarLembrete(@Valid @RequestBody Lembrete registro, @PathVariable long userId) {
        log.info("Cadastrando registro de lembrete");

        Lembrete retornoService = (Lembrete) lembreteService.registrarLembrete(registro, userId);


        var entityModel = EntityModel.of(
                retornoService,
                linkTo(methodOn(LembreteController.class).registrarLembrete(registro, userId)).withSelfRel(),
                linkTo(methodOn(LembreteController.class).deletarLembrete(userId, retornoService.getId())).withRel("deletar")
        );

        return ResponseEntity.created(linkTo(methodOn(LembreteController.class).registrarLembrete(registro, userId)).toUri())
                .body(entityModel);
    }

    @PutMapping("{id}")
    public ResponseEntity<EntityModel<Lembrete>> atualizarLembrete(@RequestBody Lembrete lembrete, @PathVariable long userId)
    {
        log.info("Atualizando produto de usuario pelo id: " + userId);

        var retornoService = lembreteService.atualizarLembrete(lembrete, userId);
        if(retornoService == null)
            throw new RestBadRequestException("Atualização não efetuada. Tente novamente com dados diferentes.");

        var produtoAtualizado = EntityModel.of(
                retornoService,
                linkTo(methodOn(LembreteController.class).atualizarLembrete(lembrete, userId)).withSelfRel(),
                linkTo(methodOn(LembreteController.class).registrarLembrete(lembrete, userId)).withRel("cadastrar"),
                linkTo(methodOn(LembreteController.class).deletarLembrete(userId, lembrete.getId())).withRel("deletar")
        );

        return ResponseEntity.ok(produtoAtualizado);
    }


    @DeleteMapping("{userId}/deletar/{registroId}")
    public ResponseEntity<Lembrete> deletarLembrete(@PathVariable long userId, @PathVariable long registroId)
    {
        log.info("Deletando registro de sono");

        lembreteService.deletarLembrete(userId, registroId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("{userId}/historico")
    public ResponseEntity<EntityModel<PaginationResponseDTO>> recuperarHistorico(@PathVariable long userId, @PageableDefault(size = 3) Pageable pageable) {
        log.info("Buscando historico");

        var historicoEncontrado = historicoService.recuperarHistorico(userId, pageable);

        var entityModel = EntityModel.of(
                historicoEncontrado,
                linkTo(methodOn(LembreteController.class).recuperarHistorico(userId, pageable)).withSelfRel()
        );

        return ResponseEntity.ok(entityModel);
    }
}
