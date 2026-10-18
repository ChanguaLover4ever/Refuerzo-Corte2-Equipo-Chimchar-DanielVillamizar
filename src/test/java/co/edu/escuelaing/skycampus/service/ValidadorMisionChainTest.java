package co.edu.escuelaing.skycampus.service;

import co.edu.escuelaing.skycampus.domain.DestinoInvalidoException;
import co.edu.escuelaing.skycampus.domain.Drone;
import co.edu.escuelaing.skycampus.domain.Mision;
import co.edu.escuelaing.skycampus.enumeration.EstadoDrone;
import co.edu.escuelaing.skycampus.enumeration.EstadoMision;
import co.edu.escuelaing.skycampus.enumeration.TipoCarga;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidadorMisionChainTest {
    private final ValidadorMisionChain cadena = ValidadorMisionChain.crearCadena();

    @Test
    void validaMisionCompletaEnLaCadena() {
        assertDoesNotThrow(() -> cadena.validar(mision(80, "Biblioteca", TipoCarga.LIBRO)));
    }

    @Test
    void rechazaDroneConBateriaMenorAlTreintaPorCiento() {
        assertThrows(IllegalStateException.class,
                () -> cadena.validar(mision(29, "Biblioteca", TipoCarga.SOBRE)));
    }

    @Test
    void rechazaDestinoFueraDelCampus() {
        assertThrows(DestinoInvalidoException.class,
                () -> cadena.validar(mision(80, "Edificio Inexistente", TipoCarga.SOBRE)));
    }

    @Test
    void rechazaLibroConBateriaMenorAlCincuentaPorCiento() {
        assertThrows(IllegalStateException.class,
                () -> cadena.validar(mision(40, "Biblioteca", TipoCarga.LIBRO)));
    }

    @Test
    void aceptaCarpetaConBateriaMinimaGeneral() {
        assertDoesNotThrow(() -> cadena.validar(mision(30, "Biblioteca", TipoCarga.CARPETA)));
    }

    @Test
    void rechazaMisionNula() {
        assertThrows(IllegalArgumentException.class, () -> cadena.validar(null));
    }

    @Test
    void validadoresPuedenEjecutarseSinUnSiguienteEslabon() {
        Mision misionValida = mision(80, "Biblioteca", TipoCarga.LIBRO);

        assertDoesNotThrow(() -> new ValidadorBateria().validar(misionValida));
        assertDoesNotThrow(() -> new ValidadorDestino().validar(misionValida));
        assertDoesNotThrow(() -> new ValidadorCarga().validar(misionValida));
    }

    private Mision mision(int bateria, String destino, TipoCarga carga) {
        Drone drone = new Drone("D-01", "Chimchar", bateria, EstadoDrone.DISPONIBLE, "Bloque C");
        return new Mision("M-01", drone, "Bloque C", destino, carga, EstadoMision.PENDIENTE);
    }
}
