package co.edu.escuelaing.skycampus.service;

import co.edu.escuelaing.skycampus.domain.Mision;

public class ValidadorBateria extends ValidadorMisionChain {
    private static final int BATERIA_MINIMA = 30;

    public ValidadorBateria() {
        this(null);
    }

    public ValidadorBateria(ValidadorMisionChain siguiente) {
        super(siguiente);
    }

    @Override
    protected void validarActual(Mision mision) {
        if (mision.drone().bateria() < BATERIA_MINIMA) {
            throw new IllegalStateException("El drone debe tener al menos 30% de batería");
        }
    }
}
