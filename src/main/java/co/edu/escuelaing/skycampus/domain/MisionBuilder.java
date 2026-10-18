package co.edu.escuelaing.skycampus.domain;

import co.edu.escuelaing.skycampus.enumeration.EstadoMision;
import co.edu.escuelaing.skycampus.enumeration.TipoCarga;

public class MisionBuilder {
    private String id;
    private Drone drone;
    private String origen;
    private String destino;
    private TipoCarga tipoCarga;
    private EstadoMision estado;

    public MisionBuilder id(String id) {
        this.id = id;
        return this;
    }

    public MisionBuilder drone(Drone drone) {
        this.drone = drone;
        return this;
    }

    public MisionBuilder origen(String origen) {
        this.origen = origen;
        return this;
    }

    public MisionBuilder destino(String destino) {
        this.destino = destino;
        return this;
    }

    public MisionBuilder tipoCarga(TipoCarga tipoCarga) {
        this.tipoCarga = tipoCarga;
        return this;
    }

    public MisionBuilder estado(EstadoMision estado) {
        this.estado = estado;
        return this;
    }

    public Mision build() {
        if (id == null || id.isBlank()) {
            throw new IllegalStateException("El id de la misión es obligatorio");
        }
        if (drone == null) {
            throw new IllegalStateException("La misión debe tener un drone");
        }
        if (origen == null || origen.isBlank()) {
            throw new IllegalStateException("El origen de la misión es obligatorio");
        }
        if (destino == null || destino.isBlank()) {
            throw new IllegalStateException("El destino de la misión es obligatorio");
        }
        if (tipoCarga == null) {
            throw new IllegalStateException("La carga de la misión es obligatoria");
        }
        if (estado == null) {
            throw new IllegalStateException("El estado de la misión es obligatorio");
        }
        return new Mision(id, drone, origen, destino, tipoCarga, estado);
    }
}
