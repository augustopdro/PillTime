package br.com.fiap.pilltime.repositories;

import br.com.fiap.pilltime.models.Lembrete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface RegistroRepository extends JpaRepository<Lembrete, Long> {
    boolean existsByIdAndUsuarioId(long registroId, long usuarioId);

    @Query("SELECT l FROM Lembrete l WHERE l.usuario.id = :usuarioId")
    Page<Lembrete> getAllRegisters(long usuarioId , Pageable pageable);
}
