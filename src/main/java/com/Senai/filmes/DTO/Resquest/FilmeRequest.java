package com.Senai.filmes.DTO.Resquest;

import com.Senai.filmes.Model.Enums.GeneroFilme;

import java.util.UUID;

public record FilmeRequest(UUID id, String titulo,String descricao, String urlPoster, GeneroFilme genero, Integer duracaoMinutos) {

}
