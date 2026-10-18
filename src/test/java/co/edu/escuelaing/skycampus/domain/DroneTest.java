package co.edu.escuelaing.skycampus.domain;

import co.edu.escuelaing.skycampus.enumeration.EstadoDrone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DroneTest {
    @Test
    void requiereEstado() {
        assertThrows(IllegalArgumentException.class,
                () -> new Drone("D-01", "DJI Mini 3", 80, null, "Bloque A"));
    }

    @Test
    void disponibilidadSeDerivaDelEstado() {
        assertTrue(drone(EstadoDrone.DISPONIBLE).disponible());
        assertFalse(drone(EstadoDrone.EN_VUELO).disponible());
        assertFalse(drone(EstadoDrone.EN_CARGA).disponible());
        assertFalse(drone(EstadoDrone.FALLO).disponible());
    }

    private Drone drone(EstadoDrone estado) {
        return new Drone("D-01", "DJI Mini 3", 80, estado, "Bloque A");
    }
}
