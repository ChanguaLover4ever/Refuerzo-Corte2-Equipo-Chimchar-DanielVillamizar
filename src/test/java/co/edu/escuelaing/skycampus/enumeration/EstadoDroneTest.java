package co.edu.escuelaing.skycampus.enumeration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void resuelveEstadoPorNombre() {
        assertEquals(EstadoDrone.DISPONIBLE, EstadoDrone.valueOf("DISPONIBLE"));
    }

    @Test
    void rechazaNombreDeEstadoInexistente() {
        assertThrows(IllegalArgumentException.class, () -> EstadoDrone.valueOf("INEXISTENTE"));
    }
}
