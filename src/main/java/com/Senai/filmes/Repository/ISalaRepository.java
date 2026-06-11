package com.Senai.filmes.Repository;

import com.Senai.filmes.Model.Sala;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ISalaRepository extends JpaRepository<Sala, UUID> {
   ;
}
