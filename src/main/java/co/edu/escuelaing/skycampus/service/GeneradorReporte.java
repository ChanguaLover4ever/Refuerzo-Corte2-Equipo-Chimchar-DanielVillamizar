package co.edu.escuelaing.skycampus.service;

import co.edu.escuelaing.skycampus.domain.Mision;

import java.util.Objects;

public class GeneradorReporte {
    public String generar(Mision mision) {
        Objects.requireNonNull(mision, "La misión no puede ser nula");
        return """
                Reporte de misión: %s
                Drone: %s (%s)
                Origen: %s
                Destino: %s
                Carga: %s
                Estado: %s
                """.formatted(
                mision.id(),
                mision.drone().id(),
                mision.drone().modelo(),
                mision.origen(),
                mision.destino(),
                mision.tipoCarga(),
                mision.estado()
        );
    }
}
