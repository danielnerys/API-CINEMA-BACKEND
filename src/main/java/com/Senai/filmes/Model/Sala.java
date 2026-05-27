package com.Senai.filmes.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.UUID;

@Data
@Entity
@Table(name = "salas")
@NoArgsConstructor
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Campo não pode ser vazio!")
    private String nome;

    @Min(value = 1L, message = "A sala deve ter pelo menos 1 assento")
    private Integer total_asssentos;

    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Assento> assentos = new ArrayList<>();




}
