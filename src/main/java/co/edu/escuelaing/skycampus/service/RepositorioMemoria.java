package co.edu.escuelaing.skycampus.service;

import co.edu.escuelaing.skycampus.domain.Mision;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RepositorioMemoria implements RepositorioMision {
    private final Map<String, Mision> misiones = new HashMap<>();

    @Override
    public void guardar(Mision mision) {
        if (mision == null) {
            throw new IllegalArgumentException("La misión no puede ser nula");
        }
        if (misiones.putIfAbsent(mision.id(), mision) != null) {
            throw new IllegalStateException("Ya existe una misión con el id " + mision.id());
        }
    }

    @Override
    public Optional<Mision> buscarPorId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El id de la misión es obligatorio");
        }
        return Optional.ofNullable(misiones.get(id));
    }
}
