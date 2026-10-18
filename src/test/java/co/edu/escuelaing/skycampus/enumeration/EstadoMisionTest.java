package co.edu.escuelaing.skycampus.enumeration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void resuelveEstadoPorNombre() {
        assertEquals(EstadoMision.PENDIENTE, EstadoMision.valueOf("PENDIENTE"));
    }

    @Test
    void rechazaNombreDeEstadoInexistente() {
        assertThrows(IllegalArgumentException.class, () -> EstadoMision.valueOf("INEXISTENTE"));
    }
}
