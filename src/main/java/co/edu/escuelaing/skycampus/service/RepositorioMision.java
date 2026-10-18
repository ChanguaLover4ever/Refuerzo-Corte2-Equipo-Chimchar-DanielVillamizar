package co.edu.escuelaing.skycampus.service;

import co.edu.escuelaing.skycampus.domain.Mision;

import java.util.Optional;

public interface RepositorioMision {
    void guardar(Mision mision);

    Optional<Mision> buscarPorId(String id);
}
