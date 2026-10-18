package co.edu.escuelaing.skycampus.service;

import co.edu.escuelaing.skycampus.domain.Mision;

public class ValidadorDestino extends ValidadorMisionChain {
    private final ValidadorMision validadorMision = new ValidadorMision();

    public ValidadorDestino() {
        this(null);
    }

    public ValidadorDestino(ValidadorMisionChain siguiente) {
        super(siguiente);
    }

    @Override
    protected void validarActual(Mision mision) {
        validadorMision.validarDestino(mision.destino());
    }
}
