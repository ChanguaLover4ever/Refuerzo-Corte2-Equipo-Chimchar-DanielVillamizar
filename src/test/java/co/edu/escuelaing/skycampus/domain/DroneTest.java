package co.edu.escuelaing.skycampus.domain;

import co.edu.escuelaing.skycampus.enumeration.EstadoDrone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DroneTest {
    @Test
    void requiereIdNoNuloNiVacio() {
        assertThrows(IllegalArgumentException.class,
                () -> new Drone(null, "DJI Mini 3", 80, EstadoDrone.DISPONIBLE, "Bloque A"));
        assertThrows(IllegalArgumentException.class,
                () -> new Drone("", "DJI Mini 3", 80, EstadoDrone.DISPONIBLE, "Bloque A"));
    }

    @Test
    void requiereModeloNoNuloNiVacio() {
        assertThrows(IllegalArgumentException.class,
                () -> new Drone("D-01", null, 80, EstadoDrone.DISPONIBLE, "Bloque A"));
        assertThrows(IllegalArgumentException.class,
                () -> new Drone("D-01", "", 80, EstadoDrone.DISPONIBLE, "Bloque A"));
    }

    @Test
    void bateriaNoPuedeSerNegativa() {
        assertThrows(IllegalArgumentException.class,
                () -> new Drone("D-01", "DJI Mini 3", -1, EstadoDrone.DISPONIBLE, "Bloque A"));
    }

    @Test
    void bateriaNoPuedeSuperarCien() {
        assertThrows(IllegalArgumentException.class,
                () -> new Drone("D-01", "DJI Mini 3", 101, EstadoDrone.DISPONIBLE, "Bloque A"));
    }

    @Test
    void requiereEstado() {
        assertThrows(IllegalArgumentException.class,
                () -> new Drone("D-01", "DJI Mini 3", 80, null, "Bloque A"));
    }

    @Test
    void requiereUbicacionNoNulaNiVacia() {
        assertThrows(IllegalArgumentException.class,
                () -> new Drone("D-01", "DJI Mini 3", 80, EstadoDrone.DISPONIBLE, null));
        assertThrows(IllegalArgumentException.class,
                () -> new Drone("D-01", "DJI Mini 3", 80, EstadoDrone.DISPONIBLE, ""));
    }

    @Test
    void permiteCrearDroneValido() {
        Drone drone = drone(EstadoDrone.DISPONIBLE);

        assertEquals("D-01", drone.id());
        assertEquals("DJI Mini 3", drone.modelo());
        assertEquals(80, drone.bateria());
        assertEquals(EstadoDrone.DISPONIBLE, drone.estado());
        assertEquals("Bloque A", drone.ubicacion());
        assertTrue(drone.disponible());
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
