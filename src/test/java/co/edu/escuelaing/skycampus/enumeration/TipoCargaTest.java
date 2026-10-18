package co.edu.escuelaing.skycampus.enumeration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TipoCargaTest {
    @Test
    void declaraLosTiposEsperados() {
        assertArrayEquals(new TipoCarga[]{
                TipoCarga.SOBRE,
                TipoCarga.CARPETA,
                TipoCarga.LIBRO
        }, TipoCarga.values());
    }
}
