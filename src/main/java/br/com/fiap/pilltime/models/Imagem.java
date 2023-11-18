package br.com.fiap.pilltime.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome_imagem",nullable = false)
    private String nomeDoArquivo;

    @OneToOne
    private Lembrete lembrete;

    public Imagem(String nomeDoArquivo) {
        this.nomeDoArquivo = nomeDoArquivo;
    }
}
