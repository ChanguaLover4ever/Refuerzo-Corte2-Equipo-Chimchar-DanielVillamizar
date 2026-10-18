package co.edu.escuelaing.skycampus.service;

import co.edu.escuelaing.skycampus.domain.Mision;

import java.util.Objects;

public class AlertaOperadorConsola implements AlertaOperador {
    private static final System.Logger LOGGER = System.getLogger(AlertaOperadorConsola.class.getName());

    @Override
    public void notificarAsignacion(Mision mision) {
        Objects.requireNonNull(mision, "La misión no puede ser nula");
        LOGGER.log(System.Logger.Level.INFO, "Misión {0} asignada al drone {1}",
                mision.id(), mision.drone().id());
    }
}
