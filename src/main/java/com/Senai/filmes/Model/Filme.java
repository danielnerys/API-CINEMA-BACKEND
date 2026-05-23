package com.Senai.filmes.Model;

import com.Senai.filmes.Model.Enums.GeneroFilme;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@Table(name = "filmes")
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O titulo é obrigatório")
    private String titulo_filme;
    @Column(columnDefinition = "TEXT")
    private String descricao_filme;

    private String urlPoster;

    @NotNull(message = "O genero não pode ser nulo")
    @Enumerated(EnumType.STRING)
    private GeneroFilme genero;
    @NotNull(message = "O campo minutos é obrigatorio")
    @Min(value = 1, message = "A duração deve ser maior que 0")
    private Integer duracaoMinutos;

}
