package co.edu.escuelaing.skycampus.service;

import co.edu.escuelaing.skycampus.domain.Mision;

public abstract class ValidadorMisionChain {
    private final ValidadorMisionChain siguiente;

    protected ValidadorMisionChain(ValidadorMisionChain siguiente) {
        this.siguiente = siguiente;
    }

    public static ValidadorMisionChain crearCadena() {
        return new ValidadorBateria(new ValidadorDestino(new ValidadorCarga(null)));
    }

    public final void validar(Mision mision) {
        if (mision == null) {
            throw new IllegalArgumentException("La misión no puede ser nula");
        }
        validarActual(mision);
        if (siguiente != null) {
            siguiente.validar(mision);
        }
    }

    protected abstract void validarActual(Mision mision);
}
