package co.edu.escuelaing.skycampus.domain;

import co.edu.escuelaing.skycampus.enumeration.EstadoDrone;
import co.edu.escuelaing.skycampus.enumeration.EstadoMision;
import co.edu.escuelaing.skycampus.enumeration.TipoCarga;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MisionBuilderTest {
    private final Drone drone = new Drone("D-01", "Chimchar", 80, EstadoDrone.DISPONIBLE, "Bloque C");

    @Test
    void construyeMisionValidaConApiFluida() {
        Mision mision = builderCompleto().build();

        assertEquals("M-01", mision.id());
        assertEquals(drone, mision.drone());
        assertEquals("Bloque C", mision.origen());
        assertEquals("Biblioteca", mision.destino());
        assertEquals(TipoCarga.LIBRO, mision.tipoCarga());
        assertEquals(EstadoMision.PENDIENTE, mision.estado());
    }

    @Test
    void requiereId() {
        assertThrows(IllegalStateException.class,
                () -> new MisionBuilder().build());
    }

    @Test
    void requiereDrone() {
        assertThrows(IllegalStateException.class,
                () -> builderCompleto().drone(null).build());
    }

    @Test
    void requiereOrigen() {
        assertThrows(IllegalStateException.class,
                () -> builderCompleto().origen(null).build());
    }

    @Test
    void requiereDestino() {
        assertThrows(IllegalStateException.class,
                () -> builderCompleto().destino(null).build());
    }

    @Test
    void requiereCarga() {
        assertThrows(IllegalStateException.class,
                () -> builderCompleto().tipoCarga(null).build());
    }

    @Test
    void requiereEstado() {
        assertThrows(IllegalStateException.class,
                () -> builderCompleto().estado(null).build());
    }

    @Test
    void rechazaCamposDeTextoEnBlanco() {
        assertThrows(IllegalStateException.class,
                () -> builderCompleto().id(" ").build());
        assertThrows(IllegalStateException.class,
                () -> builderCompleto().origen(" ").build());
        assertThrows(IllegalStateException.class,
                () -> builderCompleto().destino(" ").build());
    }

    private MisionBuilder builderCompleto() {
        return new MisionBuilder()
                .id("M-01")
                .drone(drone)
                .origen("Bloque C")
                .destino("Biblioteca")
                .tipoCarga(TipoCarga.LIBRO)
                .estado(EstadoMision.PENDIENTE);
    }
}
