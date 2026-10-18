package co.edu.escuelaing.skycampus.domain;

import co.edu.escuelaing.skycampus.enumeration.EstadoDrone;
import co.edu.escuelaing.skycampus.enumeration.EstadoMision;
import co.edu.escuelaing.skycampus.enumeration.TipoCarga;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MisionTest {
    @Test
    void requiereIdNoNuloNiVacio() {
        assertThrows(IllegalArgumentException.class,
                () -> mision(null, drone(), "Bloque A", "Biblioteca", TipoCarga.SOBRE, EstadoMision.PENDIENTE));
        assertThrows(IllegalArgumentException.class,
                () -> mision("", drone(), "Bloque A", "Biblioteca", TipoCarga.SOBRE, EstadoMision.PENDIENTE));
    }

    @Test
    void requiereDrone() {
        assertThrows(IllegalArgumentException.class,
                () -> mision("M-01", null, "Bloque A", "Biblioteca", TipoCarga.SOBRE, EstadoMision.PENDIENTE));
    }

    @Test
    void requiereOrigenNoNuloNiVacio() {
        assertThrows(IllegalArgumentException.class,
                () -> mision("M-01", drone(), null, "Biblioteca", TipoCarga.SOBRE, EstadoMision.PENDIENTE));
        assertThrows(IllegalArgumentException.class,
                () -> mision("M-01", drone(), "", "Biblioteca", TipoCarga.SOBRE, EstadoMision.PENDIENTE));
    }

    @Test
    void requiereDestinoNoNuloNiVacio() {
        assertThrows(IllegalArgumentException.class,
                () -> mision("M-01", drone(), "Bloque A", null, TipoCarga.SOBRE, EstadoMision.PENDIENTE));
        assertThrows(IllegalArgumentException.class,
                () -> mision("M-01", drone(), "Bloque A", "", TipoCarga.SOBRE, EstadoMision.PENDIENTE));
    }

    @Test
    void requiereTipoCarga() {
        assertThrows(IllegalArgumentException.class,
                () -> mision("M-01", drone(), "Bloque A", "Biblioteca", null, EstadoMision.PENDIENTE));
    }

    @Test
    void requiereEstado() {
        assertThrows(IllegalArgumentException.class,
                () -> mision("M-01", drone(), "Bloque A", "Biblioteca", TipoCarga.SOBRE, null));
    }

    @Test
    void permiteCrearMisionValida() {
        Drone drone = drone();

        Mision mision = mision("M-01", drone, "Bloque A", "Biblioteca",
                TipoCarga.SOBRE, EstadoMision.PENDIENTE);

        assertEquals("M-01", mision.id());
        assertEquals(drone, mision.drone());
        assertEquals("Bloque A", mision.origen());
        assertEquals("Biblioteca", mision.destino());
        assertEquals(TipoCarga.SOBRE, mision.tipoCarga());
        assertEquals(EstadoMision.PENDIENTE, mision.estado());
    }

    private static Mision mision(String id, Drone drone, String origen, String destino,
                                 TipoCarga tipoCarga, EstadoMision estado) {
        return new Mision(id, drone, origen, destino, tipoCarga, estado);
    }

    private static Drone drone() {
        return new Drone("D-01", "DJI Mini 3", 80, EstadoDrone.DISPONIBLE, "Bloque A");
    }
}
