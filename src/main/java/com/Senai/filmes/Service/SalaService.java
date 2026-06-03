package com.Senai.filmes.Service;

import com.Senai.filmes.DTO.Response.SalaResponse;
import com.Senai.filmes.DTO.Resquest.SalaRequest;
import com.Senai.filmes.Model.Assento;
import com.Senai.filmes.Model.Sala;
import com.Senai.filmes.Repository.ISalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalaService{
    @Autowired
    private ISalaRepository salaRepository;
    public SalaResponse cadastrarSala(SalaRequest request) {
        Sala sala = new Sala();
        sala.setNome(request.nomeSala());
        sala.setTotal_asssentos(request.fileiras() * request.assentosPorFileira());

        List<Assento> assentos = gerarAssentos(sala, request.fileiras(), request.assentosPorFileira());
        sala.setAssentos(assentos);

        return toResponse(salaRepository.save(sala));
    }

    private List<Assento> gerarAssentos(Sala sala, int fileiras, int assentosPorFileira) {
        List<Assento> assentos = new ArrayList<>();
        for (int f = 0; f < fileiras; f++) {
            String fileira = String.valueOf((char) ('A' + f));
            for (int n = 1; n <= assentosPorFileira; n++) {
                Assento assento = new Assento();
                assento.setSala(sala);
                assento.setFileira(fileira);
                assento.setNumero(n);
                assentos.add(assento);
            }
        }
        return assentos;
    }

    private SalaResponse toResponse(Sala sala) {
        return new SalaResponse(
                sala.getId(),
                sala.getNome(),
                sala.getTotal_asssentos()
        );
    }
}