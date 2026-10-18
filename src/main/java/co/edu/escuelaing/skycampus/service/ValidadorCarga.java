package co.edu.escuelaing.skycampus.service;

import co.edu.escuelaing.skycampus.domain.Mision;
import co.edu.escuelaing.skycampus.enumeration.TipoCarga;

public class ValidadorCarga extends ValidadorMisionChain {
    private static final int BATERIA_MINIMA_PARA_LIBRO = 50;

    public ValidadorCarga() {
        this(null);
    }

    public ValidadorCarga(ValidadorMisionChain siguiente) {
        super(siguiente);
    }

    @Override
    protected void validarActual(Mision mision) {
        // La regla de capacidad para Chimchar reserva los libros para vuelos con al menos 50% de batería.
        if (mision.tipoCarga() == TipoCarga.LIBRO
                && mision.drone().bateria() < BATERIA_MINIMA_PARA_LIBRO) {
            throw new IllegalStateException("El drone requiere al menos 50% de batería para transportar un libro");
        }
    }
}
