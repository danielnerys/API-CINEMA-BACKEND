package com.Senai.filmes.DTO.Resquest;

import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record SessaoRequest(UUID salaId, UUID filmeId, LocalDateTime inicio, LocalDateTime fim, BigDecimal preco) {
}
