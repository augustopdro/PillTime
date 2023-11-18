package br.com.fiap.pilltime.repositories;

import br.com.fiap.pilltime.models.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagemRepository extends JpaRepository<Imagem, Long> {

}
