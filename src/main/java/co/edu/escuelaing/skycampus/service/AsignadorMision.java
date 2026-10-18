package co.edu.escuelaing.skycampus.service;

import co.edu.escuelaing.skycampus.domain.Drone;
import co.edu.escuelaing.skycampus.domain.Mision;
import co.edu.escuelaing.skycampus.enumeration.EstadoMision;
import co.edu.escuelaing.skycampus.enumeration.TipoCarga;

import java.util.List;
import java.util.Objects;

public class AsignadorMision {
    private final EstrategiaAsignacionDrone estrategia;
    private final RepositorioMision repositorio;
    private final AlertaOperador alertaOperador;

    public AsignadorMision(
            EstrategiaAsignacionDrone estrategia,
            RepositorioMision repositorio,
            AlertaOperador alertaOperador
    ) {
        this.estrategia = Objects.requireNonNull(estrategia, "La estrategia de asignación es obligatoria");
        this.repositorio = Objects.requireNonNull(repositorio, "El repositorio de misiones es obligatorio");
        this.alertaOperador = Objects.requireNonNull(alertaOperador, "La alerta al operador es obligatoria");
    }

    public Mision asignar(
            String id,
            List<Drone> drones,
            String origen,
            String destino,
            TipoCarga tipoCarga
    ) {
        if (repositorio.buscarPorId(id).isPresent()) {
            throw new IllegalStateException("Ya existe una misión con el id " + id);
        }

        Drone drone = estrategia.seleccionar(drones);
        Mision mision = new Mision(id, drone, origen, destino, tipoCarga, EstadoMision.PENDIENTE);
        repositorio.guardar(mision);
        alertaOperador.notificarAsignacion(mision);
        return mision;
    }
}
