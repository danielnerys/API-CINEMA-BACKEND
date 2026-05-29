package com.Senai.filmes.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "sessoes")
public class Sessao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "filme_id")
    @NotNull
    private Filme filme;

    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    @NotNull
    private Sala sala;

    @NotNull(message = "O campo não pode ser nulo.")
    private LocalDateTime inicio;

    @NotNull(message = "O campo não pode ser nulo.")
    private LocalDateTime fim;

    @NotNull(message = "O preço não pode ser nulo")
    private BigDecimal preco;



}
