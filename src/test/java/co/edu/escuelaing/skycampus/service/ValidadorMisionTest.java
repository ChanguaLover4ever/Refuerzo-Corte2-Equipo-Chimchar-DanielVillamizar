package co.edu.escuelaing.skycampus.service;

import co.edu.escuelaing.skycampus.domain.Drone;
import co.edu.escuelaing.skycampus.domain.DestinoInvalidoException;
import co.edu.escuelaing.skycampus.enumeration.EstadoDrone;
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
        assertTrue(validador.tieneBateriaSuficiente(drone(30, EstadoDrone.DISPONIBLE)));
    }

    @Test
    void bateriaMenorAlMinimoNoEsSuficiente() {
        assertFalse(validador.tieneBateriaSuficiente(drone(29, EstadoDrone.DISPONIBLE)));
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
        assertThrows(DestinoInvalidoException.class,
                () -> validador.validarDestino("Edificio Inexistente"));
    }

    @Test
    void destinoNuloNoEsValido() {
        assertThrows(DestinoInvalidoException.class,
                () -> validador.validarDestino(null));
    }

    @Test
    void droneDisponiblePuedeUsarse() {
        assertTrue(validador.droneEstaDisponible(drone(80, EstadoDrone.DISPONIBLE)));
    }

    @Test
    void droneEnVueloNoPuedeUsarse() {
        assertFalse(validador.droneEstaDisponible(drone(80, EstadoDrone.EN_VUELO)));
    }

    @Test
    void droneEnCargaNoPuedeUsarse() {
        assertFalse(validador.droneEstaDisponible(drone(80, EstadoDrone.EN_CARGA)));
    }

    @Test
    void droneEnFalloNoPuedeUsarse() {
        assertFalse(validador.droneEstaDisponible(drone(80, EstadoDrone.FALLO)));
    }

    @Test
    void droneNuloNoEstaDisponible() {
        assertFalse(validador.droneEstaDisponible(null));
    }

    private Drone drone(int bateria, EstadoDrone estado) {
        return new Drone("D-01", "DJI Mini 3", bateria, estado, "Bloque A");
    }
}
