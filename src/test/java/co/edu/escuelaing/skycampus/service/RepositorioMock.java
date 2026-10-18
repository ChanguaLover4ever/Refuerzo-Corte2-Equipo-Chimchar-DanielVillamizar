package co.edu.escuelaing.skycampus.service;

import co.edu.escuelaing.skycampus.domain.Mision;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class RepositorioMock implements RepositorioMision {
    private final Map<String, Mision> misiones = new HashMap<>();

    @Override
    public void guardar(Mision mision) {
        misiones.put(mision.id(), mision);
    }

    @Override
    public Optional<Mision> buscarPorId(String id) {
        return Optional.ofNullable(misiones.get(id));
    }
}
