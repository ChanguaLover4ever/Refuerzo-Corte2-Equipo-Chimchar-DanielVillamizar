package co.edu.escuelaing.skycampus.service;

import co.edu.escuelaing.skycampus.domain.Drone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidadorMisionTest {
    private ValidadorMision validador;

    @BeforeEach
    void setUp() {
        validador = new ValidadorMision();
    }

    @Test
    void bateriaDeTreintaPorCientoEsSuficiente() {
        assertTrue(validador.tieneBateriaSuficiente(drone(30, true)));
    }

    @Test
    void bateriaMenorAlMinimoNoEsSuficiente() {
        assertFalse(validador.tieneBateriaSuficiente(drone(29, true)));
    }

    @Test
    void droneNuloNoTieneBateriaSuficiente() {
        assertFalse(validador.tieneBateriaSuficiente(null));
    }

    @Test
    void destinoDelCampusEsValido() {
        assertDoesNotThrow(() -> validador.validarDestino("Biblioteca"));
    }

    @Test
    void destinoDesconocidoNoEsValido() {
        assertThrows(IllegalArgumentException.class,
                () -> validador.validarDestino("Edificio Inexistente"));
    }

    @Test
    void destinoNuloNoEsValido() {
        assertThrows(IllegalArgumentException.class,
                () -> validador.validarDestino(null));
    }

    @Test
    void droneDisponiblePuedeUsarse() {
        assertTrue(validador.droneEstaDisponible(drone(80, true)));
    }

    @Test
    void droneNoDisponibleNoPuedeUsarse() {
        assertFalse(validador.droneEstaDisponible(drone(80, false)));
    }

    @Test
    void droneNuloNoEstaDisponible() {
        assertFalse(validador.droneEstaDisponible(null));
    }

    private Drone drone(int bateria, boolean disponible) {
        return new Drone("D-01", "DJI Mini 3", bateria, disponible, "Bloque A");
    }
}
