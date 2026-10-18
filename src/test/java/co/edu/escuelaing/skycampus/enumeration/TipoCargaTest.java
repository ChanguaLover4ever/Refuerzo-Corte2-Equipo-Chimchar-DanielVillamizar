package co.edu.escuelaing.skycampus.enumeration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TipoCargaTest {
    @Test
    void declaraLosTiposEsperados() {
        assertArrayEquals(new TipoCarga[]{
                TipoCarga.SOBRE,
                TipoCarga.CARPETA,
                TipoCarga.LIBRO
        }, TipoCarga.values());
    }

    @Test
    void resuelveTipoPorNombre() {
        assertEquals(TipoCarga.SOBRE, TipoCarga.valueOf("SOBRE"));
    }

    @Test
    void rechazaNombreDeTipoInexistente() {
        assertThrows(IllegalArgumentException.class, () -> TipoCarga.valueOf("INEXISTENTE"));
    }
}
