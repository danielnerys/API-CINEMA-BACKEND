package com.Senai.filmes.Model;

import com.Senai.filmes.Model.Enums.CargoUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
@Table(name="usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O nome não pode ser vazio.")
    private String nome_usuario;
    @NotBlank(message = "Email é obrigatório")
    @Column(unique = true)
    private String email_usuario;

    @NotBlank(message = "A SENHA É OBRIGATÓRIA")
    private String senha;

    @NotNull(message = "O campo cargo não pode ser nulo.")
    @Enumerated(EnumType.STRING)
    private CargoUsuario cargo;

    @CreationTimestamp
    private LocalDateTime criado_em;



}
