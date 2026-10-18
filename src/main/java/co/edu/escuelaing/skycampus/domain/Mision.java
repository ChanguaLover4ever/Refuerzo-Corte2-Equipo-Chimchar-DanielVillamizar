package co.edu.escuelaing.skycampus.domain;

import co.edu.escuelaing.skycampus.enumeration.EstadoMision;
import co.edu.escuelaing.skycampus.enumeration.TipoCarga;

public record Mision(
        String id,
        Drone drone,
        String origen,
        String destino,
        TipoCarga tipoCarga,
        EstadoMision estado
) {
    public Mision {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El id de la misión es obligatorio");
        }
        if (drone == null) {
            throw new IllegalArgumentException("La misión debe tener un drone");
        }
        if (origen == null || origen.isBlank()) {
            throw new IllegalArgumentException("El origen de la misión es obligatorio");
        }
        if (destino == null || destino.isBlank()) {
            throw new IllegalArgumentException("El destino de la misión es obligatorio");
        }
        if (tipoCarga == null || estado == null) {
            throw new IllegalArgumentException("La carga y el estado de la misión son obligatorios");
        }
    }
}
