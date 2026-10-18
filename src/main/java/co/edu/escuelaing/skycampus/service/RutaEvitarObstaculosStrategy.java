package co.edu.escuelaing.skycampus.service;

import java.util.List;
import java.util.Objects;

public class RutaEvitarObstaculosStrategy implements EstrategiaRuta {
    private final String puntoIntermedio;

    public RutaEvitarObstaculosStrategy(String puntoIntermedio) {
        if (puntoIntermedio == null || puntoIntermedio.isBlank()) {
            throw new IllegalArgumentException("El punto intermedio es obligatorio");
        }
        this.puntoIntermedio = puntoIntermedio;
    }

    @Override
    public List<String> calcularRuta(String origen, String destino) {
        Objects.requireNonNull(origen, "El origen es obligatorio");
        Objects.requireNonNull(destino, "El destino es obligatorio");
        return List.of(origen, puntoIntermedio, destino);
    }
}
