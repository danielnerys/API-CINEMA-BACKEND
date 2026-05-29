package com.Senai.filmes.DTO.Resquest;

import java.util.List;
import java.util.UUID;

public record ReservaRequest(UUID id, List<UUID> assentoIds) {
}
