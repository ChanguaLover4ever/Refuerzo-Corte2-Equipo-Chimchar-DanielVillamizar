package co.edu.escuelaing.skycampus.service;

import co.edu.escuelaing.skycampus.domain.Drone;
import co.edu.escuelaing.skycampus.enumeration.EstadoDrone;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EstrategiaAsignacionDroneTest {
    private final Drone enVuelo = drone("D-01", 100, EstadoDrone.EN_VUELO);
    private final Drone disponiblePrimero = drone("D-02", 45, EstadoDrone.DISPONIBLE);
    private final Drone disponibleMayorBateria = drone("D-03", 85, EstadoDrone.DISPONIBLE);

    @Test
    void estrategiaMayorBateriaSeleccionaElDisponibleConMasBateria() {
        Drone seleccionado = new AsignacionMayorBateriaStrategy()
                .seleccionar(List.of(enVuelo, disponiblePrimero, disponibleMayorBateria));

        assertEquals(disponibleMayorBateria, seleccionado);
    }

    @Test
    void estrategiaPrimerDisponibleRespetaElOrdenDeLaLista() {
        Drone seleccionado = new AsignacionPrimerDisponibleStrategy()
                .seleccionar(List.of(enVuelo, disponiblePrimero, disponibleMayorBateria));

        assertEquals(disponiblePrimero, seleccionado);
    }

    @Test
    void estrategiasRechazanListaNula() {
        assertThrows(IllegalArgumentException.class,
                () -> new AsignacionMayorBateriaStrategy().seleccionar(null));
        assertThrows(IllegalArgumentException.class,
                () -> new AsignacionPrimerDisponibleStrategy().seleccionar(null));
    }

    @Test
    void estrategiasRechazanFlotaSinDronesDisponibles() {
        List<Drone> flota = List.of(enVuelo);

        assertThrows(IllegalStateException.class,
                () -> new AsignacionMayorBateriaStrategy().seleccionar(flota));
        assertThrows(IllegalStateException.class,
                () -> new AsignacionPrimerDisponibleStrategy().seleccionar(flota));
    }

    private Drone drone(String id, int bateria, EstadoDrone estado) {
        return new Drone(id, "Chimchar", bateria, estado, "Bloque C");
    }
}
