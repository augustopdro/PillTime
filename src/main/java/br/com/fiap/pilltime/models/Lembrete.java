package br.com.fiap.pilltime.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Entity
public class Lembrete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String nome;

    @NotNull
    @Column(nullable = false)
    private String dosagem;

    @NotNull
    @Column(nullable = false)
    private LocalDate dataInicial;

    @NotNull
    @Column(nullable = false)
    private LocalTime horarioInicial;

    @NotNull
    @Column(nullable = false)
    private Integer intervalo;

    @Column(nullable = true)
    private LocalDate dataFinal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    @JsonIgnore
    private Usuario usuario;

    @OneToOne
    private Imagem imagem;

    public Lembrete(String nome, String dosagem, LocalDate dataInicial, LocalTime horarioInicial, Integer intervalo, LocalDate dataFinal) {
        this.nome = nome;
        this.dosagem = dosagem;
        this.dataInicial = dataInicial;
        this.horarioInicial = horarioInicial;
        this.intervalo = intervalo;
        this.dataFinal = dataFinal;
    }

    @Override
    public String toString() {
        return "Lembrete{" +
                "nome='" + nome + '\'' +
                ", dosagem='" + dosagem + '\'' +
                ", dataInicial=" + dataInicial +
                ", horarioInicial=" + horarioInicial +
                ", intervalo=" + intervalo +
                ", dataFinal=" + dataFinal +
                '}';
    }
}
