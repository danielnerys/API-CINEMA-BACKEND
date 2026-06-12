package com.Senai.filmes.Repository;

import com.Senai.filmes.Model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IReservaRepository extends JpaRepository<Reserva, UUID> {
    List<Reserva> findAllByUsuarioId(UUID usuarioId);

}
