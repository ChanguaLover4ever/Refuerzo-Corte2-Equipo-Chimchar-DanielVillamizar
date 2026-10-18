package co.edu.escuelaing.skycampus.service;

import co.edu.escuelaing.skycampus.domain.Mision;

@FunctionalInterface
public interface AlertaOperador {
    void notificarAsignacion(Mision mision);
}
