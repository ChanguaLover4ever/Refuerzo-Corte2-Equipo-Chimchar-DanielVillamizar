package co.edu.escuelaing.skycampus.service;

import co.edu.escuelaing.skycampus.domain.Drone;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ConsultasFlota {
    private static final int BATERIA_MINIMA_DISPONIBLE = 50;
    private static final int BATERIA_CRITICA = 20;

    public List<String> idsDisponiblesConBateriaMinima(List<Drone> flota) {
        return flota.stream()
                .filter(Drone::disponible)
                .filter(drone -> drone.bateria() >= BATERIA_MINIMA_DISPONIBLE)
                .sorted(Comparator.comparingInt(Drone::bateria).reversed())
                .map(Drone::id)
                .collect(Collectors.toList());
    }

    public boolean hayDisponibleEnUbicacion(List<Drone> flota, String ubicacion) {
        return flota.stream()
                .anyMatch(drone -> drone.disponible() && drone.ubicacion().equals(ubicacion));
    }

    public long contarDronesConBateriaCritica(List<Drone> flota) {
        return flota.stream()
                .filter(drone -> drone.bateria() < BATERIA_CRITICA)
                .count();
    }

    public List<String> resumenBateria(List<Drone> flota) {
        return flota.stream()
                .map(drone -> drone.id() + ": " + drone.bateria() + "%")
                .collect(Collectors.toList());
    }
}
