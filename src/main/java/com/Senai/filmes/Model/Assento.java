package com.Senai.filmes.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "assentos")
public class Assento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String fileira;

    @NotNull(message = "O numero do assento é obrigatório")
    private Integer numero;




    @ManyToOne
    @JoinColumn(name = "id")
    private Sala sala;
}
