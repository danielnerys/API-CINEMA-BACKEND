package com.Senai.filmes.Repository;

import com.Senai.filmes.Model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.List;
import java.util.UUID;

@Repository
public interface IFilmeRepository extends JpaRepository<Filme, UUID> {
    List<Filme> findAllBy(UUID id);

}
