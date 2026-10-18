package co.edu.escuelaing.skycampus.service;

import co.edu.escuelaing.skycampus.domain.Drone;

import java.util.List;

public class AsignacionPrimerDisponibleStrategy implements EstrategiaAsignacionDrone {
    @Override
    public Drone seleccionar(List<Drone> drones) {
        if (drones == null) {
            throw new IllegalArgumentException("La lista de drones no puede ser nula");
        }
        return drones.stream()
                .filter(Drone::disponible)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No hay drones disponibles para asignar"));
    }
}
