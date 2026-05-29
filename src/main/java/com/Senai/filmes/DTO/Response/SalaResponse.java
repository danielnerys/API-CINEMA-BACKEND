package com.Senai.filmes.DTO.Response;

import java.util.UUID;

public record SalaResponse(UUID id, String nome, Integer totalAssentos) {
}
