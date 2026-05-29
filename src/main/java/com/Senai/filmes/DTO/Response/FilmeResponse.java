package com.Senai.filmes.DTO.Response;

import com.Senai.filmes.Model.Enums.GeneroFilme;

import java.util.UUID;

public record FilmeResponse(UUID id, String titulo, String descricao, String urlPoster, GeneroFilme genero, Integer duracaoMinutos) {
}
