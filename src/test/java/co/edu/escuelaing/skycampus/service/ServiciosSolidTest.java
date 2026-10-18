package co.edu.escuelaing.skycampus.service;

import co.edu.escuelaing.skycampus.domain.Drone;
import co.edu.escuelaing.skycampus.domain.Mision;
import co.edu.escuelaing.skycampus.enumeration.EstadoDrone;
import co.edu.escuelaing.skycampus.enumeration.EstadoMision;
import co.edu.escuelaing.skycampus.enumeration.TipoCarga;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ServiciosSolidTest {
    private final Drone drone = new Drone(
            "D-01", "Chimchar", 90, EstadoDrone.DISPONIBLE, "Bloque C");

    @Test
    void asignadorSeleccionaGuardaYNotificaLaMision() {
        RepositorioMock repositorio = new RepositorioMock();
        AtomicReference<Mision> misionNotificada = new AtomicReference<>();
        AsignadorMision asignador = new AsignadorMision(
                new AsignacionPrimerDisponibleStrategy(),
                repositorio,
                misionNotificada::set
        );

        Mision mision = asignador.asignar(
                "M-01", List.of(drone), "Bloque C", "Biblioteca", TipoCarga.LIBRO);

        assertEquals(drone, mision.drone());
        assertEquals(EstadoMision.PENDIENTE, mision.estado());
        assertEquals(mision, repositorio.buscarPorId("M-01").orElseThrow());
        assertEquals(mision, misionNotificada.get());
    }

    @Test
    void asignadorRechazaIdsDeMisionDuplicados() {
        RepositorioMock repositorio = new RepositorioMock();
        AsignadorMision asignador = new AsignadorMision(
                new AsignacionPrimerDisponibleStrategy(), repositorio, mision -> { });
        asignador.asignar("M-01", List.of(drone), "Bloque C", "Biblioteca", TipoCarga.SOBRE);

        assertThrows(IllegalStateException.class, () -> asignador.asignar(
                "M-01", List.of(drone), "Bloque C", "Bloque A", TipoCarga.CARPETA));
    }

    @Test
    void generadorReportePresentaLosDatosDeLaMision() {
        Mision mision = crearMision();

        String reporte = new GeneradorReporte().generar(mision);

        assertTrue(reporte.contains("Reporte de misión: M-01"));
        assertTrue(reporte.contains("Drone: D-01 (Chimchar)"));
        assertTrue(reporte.contains("Origen: Bloque C"));
        assertTrue(reporte.contains("Destino: Biblioteca"));
        assertTrue(reporte.contains("Carga: LIBRO"));
        assertTrue(reporte.contains("Estado: PENDIENTE"));
    }

    @Test
    void estrategiasIncluidasCalculanRutasDirectasYConDesvio() {
        ServicioRuta rutaDirecta = new ServicioRuta(new RutaDirectaStrategy());
        ServicioRuta rutaConDesvio = new ServicioRuta(new RutaEvitarObstaculosStrategy("Punto seguro"));

        assertEquals(List.of("Bloque C", "Biblioteca"),
                rutaDirecta.calcularRuta("Bloque C", "Biblioteca"));
        assertEquals(List.of("Bloque C", "Punto seguro", "Biblioteca"),
                rutaConDesvio.calcularRuta("Bloque C", "Biblioteca"));
    }

    @Test
    void servicioRutaAceptaUnaNuevaEstrategiaSinModificarlo() {
        EstrategiaRuta nuevaEstrategia = (origen, destino) ->
                List.of(origen, "Estación de recarga", destino);
        ServicioRuta servicio = new ServicioRuta(nuevaEstrategia);

        assertEquals(List.of("Bloque C", "Estación de recarga", "Biblioteca"),
                servicio.calcularRuta("Bloque C", "Biblioteca"));
    }

    @Test
    void servicioRutaValidaExtremosAntesDeDelegar() {
        ServicioRuta servicio = new ServicioRuta(new RutaDirectaStrategy());

        assertThrows(IllegalArgumentException.class, () -> servicio.calcularRuta(null, "Biblioteca"));
        assertThrows(IllegalArgumentException.class, () -> servicio.calcularRuta(" ", "Biblioteca"));
        assertThrows(IllegalArgumentException.class, () -> servicio.calcularRuta("Bloque C", null));
        assertThrows(IllegalArgumentException.class, () -> servicio.calcularRuta("Bloque C", " "));
        assertThrows(NullPointerException.class, () -> new ServicioRuta(null));
    }

    @Test
    void servicioRutaRechazaEstrategiasQueIncumplenElContrato() {
        ServicioRuta servicio = new ServicioRuta((origen, destino) -> List.of("Punto incorrecto", destino));

        assertThrows(IllegalStateException.class,
                () -> servicio.calcularRuta("Bloque C", "Biblioteca"));
    }

    @Test
    void servicioRutaRechazaResultadosNulosIncompletosOVacios() {
        List<EstrategiaRuta> estrategiasInvalidas = List.of(
                (origen, destino) -> null,
                (origen, destino) -> List.of(origen),
                (origen, destino) -> List.of("Bloque incorrecto", destino),
                (origen, destino) -> List.of(origen, "Destino incorrecto"),
                (origen, destino) -> List.of(origen, " ", destino),
                (origen, destino) -> Arrays.asList(origen, null, destino)
        );

        estrategiasInvalidas.forEach(estrategia -> assertThrows(
                IllegalStateException.class,
                () -> new ServicioRuta(estrategia).calcularRuta("Bloque C", "Biblioteca")
        ));
    }

    @Test
    void repositorioMemoriaGuardaYBuscaMisiones() {
        RepositorioMision repositorio = new RepositorioMemoria();
        Mision mision = crearMision();

        repositorio.guardar(mision);

        assertEquals(mision, repositorio.buscarPorId("M-01").orElseThrow());
    }

    @Test
    void repositorioMemoriaValidaMisionesIdsYDuplicados() {
        RepositorioMemoria repositorio = new RepositorioMemoria();
        Mision mision = crearMision();
        repositorio.guardar(mision);

        assertThrows(IllegalArgumentException.class, () -> repositorio.guardar(null));
        assertThrows(IllegalStateException.class, () -> repositorio.guardar(mision));
        assertThrows(IllegalArgumentException.class, () -> repositorio.buscarPorId(" "));
        assertThrows(IllegalArgumentException.class, () -> repositorio.buscarPorId(null));
    }

    @Test
    void alertaDeConsolaAceptaUnaMisionAsignada() {
        new AlertaOperadorConsola().notificarAsignacion(crearMision());
    }

    @Test
    void estrategiaDeDesvioValidaSuPuntoIntermedio() {
        assertThrows(IllegalArgumentException.class, () -> new RutaEvitarObstaculosStrategy(" "));
        assertThrows(IllegalArgumentException.class, () -> new RutaEvitarObstaculosStrategy(null));

        RutaEvitarObstaculosStrategy estrategia = new RutaEvitarObstaculosStrategy("Punto seguro");
        assertThrows(NullPointerException.class, () -> estrategia.calcularRuta(null, "Biblioteca"));
        assertThrows(NullPointerException.class, () -> estrategia.calcularRuta("Bloque C", null));
    }

    @Test
    void asignadorDependeDeContratosIntercambiables() {
        RepositorioMock repositorio = new RepositorioMock();
        AtomicReference<Mision> notificacion = new AtomicReference<>();
        AsignadorMision asignador = new AsignadorMision(
                drones -> drones.get(0),
                repositorio,
                notificacion::set
        );

        Mision asignada = asignador.asignar(
                "M-02", List.of(drone), "Bloque C", "Bloque A", TipoCarga.CARPETA);

        assertEquals(asignada, repositorio.buscarPorId("M-02").orElseThrow());
        assertEquals(asignada, notificacion.get());
    }

    private Mision crearMision() {
        return new Mision(
                "M-01", drone, "Bloque C", "Biblioteca", TipoCarga.LIBRO, EstadoMision.PENDIENTE);
    }
}
