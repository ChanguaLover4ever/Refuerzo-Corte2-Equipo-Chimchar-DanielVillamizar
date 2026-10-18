package co.edu.escuelaing.skycampus.enumeration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class EstadoDroneTest {
    @Test
    void declaraLosEstadosEsperados() {
        assertArrayEquals(new EstadoDrone[]{
                EstadoDrone.DISPONIBLE,
                EstadoDrone.EN_VUELO,
                EstadoDrone.EN_CARGA,
                EstadoDrone.FALLO
        }, EstadoDrone.values());
    }
}
