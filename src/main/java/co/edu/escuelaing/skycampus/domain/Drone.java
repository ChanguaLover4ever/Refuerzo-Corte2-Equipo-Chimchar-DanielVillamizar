package co.edu.escuelaing.skycampus.domain;

public record Drone(
        String id,
        String modelo,
        int bateria,
        boolean disponible,
        String ubicacion
) {
    public Drone {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El id del drone es obligatorio");
        }
        if (modelo == null || modelo.isBlank()) {
            throw new IllegalArgumentException("El modelo del drone es obligatorio");
        }
        if (bateria < 0 || bateria > 100) {
            throw new IllegalArgumentException("La batería debe estar entre 0 y 100");
        }
        if (ubicacion == null || ubicacion.isBlank()) {
            throw new IllegalArgumentException("La ubicación del drone es obligatoria");
        }
    }
}
