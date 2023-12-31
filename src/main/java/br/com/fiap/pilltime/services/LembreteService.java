package br.com.fiap.pilltime.services;


import br.com.fiap.pilltime.exceptions.RestNotFoundException;
import br.com.fiap.pilltime.models.Lembrete;
import br.com.fiap.pilltime.models.Usuario;
import br.com.fiap.pilltime.repositories.LembreteRepository;
import br.com.fiap.pilltime.repositories.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class LembreteService {

	private LembreteRepository repository;
	private UsuarioRepository usuarioRepository;

	Logger log = LoggerFactory.getLogger(LembreteService.class);

	@Autowired
	public LembreteService(LembreteRepository repository, UsuarioRepository usuarioRepository) {
		this.repository = repository;
		this.usuarioRepository = usuarioRepository;
	}

	public Lembrete registrarLembrete(Lembrete lembrete, long userId) {
		log.info("Registrando um lembrete: ");

		var usuario = recuperarUsuario(userId);
		var lembretes = usuario.getLembrete();

		lembrete.setUsuario(usuario);
		lembretes.add(lembrete);

		//var registroSalvo = salvarLembrete(lembrete);

		return salvarLembrete(lembrete);
	}

	@Transactional
	public void deletarLembrete(long userId, long registroId) {
		var usuario = recuperarUsuario(userId);

		var lembrete = recuperarLembrete(registroId);

		if (!lembrete.getUsuario().equals(usuario)) {
			log.info("getid: " + lembrete.getUsuario().getId());
			throw new RestNotFoundException("Lembrete informado não pertence ao Usuário informado");
		}

		usuario.getLembrete().remove(lembrete);
		usuarioRepository.save(usuario);
		repository.delete(lembrete);
	}

	private Lembrete salvarLembrete(Lembrete lembrete){
		log.info("Registrando um lembrete: " + lembrete);
		return repository.save(lembrete);
	}


	public Lembrete atualizarLembrete(Lembrete lembrete, long id) {
		var lembreteExistente = recuperarLembrete(id);
		lembrete.setId(lembreteExistente.getId());
		lembreteExistente.setNome(lembrete.getNome());
		lembreteExistente.setDosagem(lembrete.getDosagem());
		lembreteExistente.setDataInicial(lembrete.getDataInicial());
		lembreteExistente.setHorarioInicial(lembrete.getHorarioInicial());
		lembreteExistente.setIntervalo(lembrete.getIntervalo());
		lembreteExistente.setDataFinal(lembrete.getDataFinal());
		lembreteExistente.setArquivoImagem(lembrete.getArquivoImagem());


		return atualizarLembrete(lembreteExistente);
	}

	private Usuario recuperarUsuario(long userId) {
		log.info("Recuperando usuario com id: " + userId);

		Usuario usuario = usuarioRepository
				.findById(userId)
				.orElseThrow(() -> new RestNotFoundException("Usuario não encontrado"));

		return usuario;
	}

	private Lembrete recuperarLembrete(long registroId) {
		log.info("Recuperando lembrete com id: " + registroId);

		Lembrete lembrete = repository
				.findById(registroId)
				.orElseThrow(() -> new RestNotFoundException("Lembrete não encontrado"));

		return lembrete;
	}

	private Lembrete atualizarLembrete(Lembrete lembrete) {
		log.info("Atualizando o lembrete do usuario de id: " + lembrete.getId());

		return repository.save(lembrete);
	}
}
