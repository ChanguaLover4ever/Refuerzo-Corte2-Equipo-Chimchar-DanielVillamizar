package co.edu.escuelaing.skycampus.service;

import co.edu.escuelaing.skycampus.domain.Drone;
import co.edu.escuelaing.skycampus.enumeration.EstadoDrone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConsultasFlotaTest {
    private ConsultasFlota consultas;
    private List<Drone> flota;

    @BeforeEach
    void setUp() {
        consultas = new ConsultasFlota();
        flota = List.of(
                drone("D-03", 50, EstadoDrone.DISPONIBLE, "Bloque C"),
                drone("D-01", 85, EstadoDrone.DISPONIBLE, "Bloque A"),
                drone("D-02", 92, EstadoDrone.EN_VUELO, "Bloque C"),
                drone("D-04", 18, EstadoDrone.EN_CARGA, "Bloque B"),
                drone("D-05", 12, EstadoDrone.FALLO, "Biblioteca"),
                drone("D-06", 20, EstadoDrone.DISPONIBLE, "Bloque C"),
                drone("D-07", 48, EstadoDrone.DISPONIBLE, "Bloque C")
        );
    }

    @Test
    void obtieneIdsDisponiblesConBateriaMinimaEnOrdenDescendente() {
        assertEquals(List.of("D-01", "D-03"),
                consultas.idsDisponiblesConBateriaMinima(flota));
    }

    @Test
    void detectaDroneDisponibleEnLaUbicacionIndicada() {
        assertTrue(consultas.hayDisponibleEnUbicacion(flota, "Bloque C"));
        assertFalse(consultas.hayDisponibleEnUbicacion(flota, "Bloque B"));
    }

    @Test
    void cuentaBateriaCriticaSinImportarElEstado() {
        assertEquals(2, consultas.contarDronesConBateriaCritica(flota));
    }

    @Test
    void generaResumenDeBateriaParaTodosLosDrones() {
        assertEquals(List.of(
                "D-03: 50%",
                "D-01: 85%",
                "D-02: 92%",
                "D-04: 18%",
                "D-05: 12%",
                "D-06: 20%",
                "D-07: 48%"
        ), consultas.resumenBateria(flota));
    }

    private Drone drone(String id, int bateria, EstadoDrone estado, String ubicacion) {
        return new Drone(id, "DJI Mini 3", bateria, estado, ubicacion);
    }
}
