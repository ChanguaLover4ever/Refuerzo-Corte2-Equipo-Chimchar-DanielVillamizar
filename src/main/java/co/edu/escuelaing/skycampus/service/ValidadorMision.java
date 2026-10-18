package co.edu.escuelaing.skycampus.service;

import co.edu.escuelaing.skycampus.domain.Drone;

import java.util.Set;

public class ValidadorMision {
    private static final int BATERIA_MINIMA = 30;
    private static final Set<String> DESTINOS_VALIDOS = Set.of(
            "Bloque A",
            "Bloque B",
            "Bloque C",
            "Bloque D",
            "Biblioteca"
    );

    public boolean tieneBateriaSuficiente(Drone drone) {
        return drone != null && drone.bateria() >= BATERIA_MINIMA;
    }

    public void validarDestino(String destino) {
        if (destino == null || !DESTINOS_VALIDOS.contains(destino)) {
            throw new IllegalArgumentException("El destino no existe en el campus: " + destino);
        }
    }

    public boolean droneEstaDisponible(Drone drone) {
        return drone != null && drone.disponible();
    }
}
