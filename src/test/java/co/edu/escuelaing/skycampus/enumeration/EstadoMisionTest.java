package co.edu.escuelaing.skycampus.enumeration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class EstadoMisionTest {
    @Test
    void declaraLosEstadosEsperados() {
        assertArrayEquals(new EstadoMision[]{
                EstadoMision.PENDIENTE,
                EstadoMision.EN_VUELO,
                EstadoMision.ENTREGADA,
                EstadoMision.FALLIDA
        }, EstadoMision.values());
    }
}
