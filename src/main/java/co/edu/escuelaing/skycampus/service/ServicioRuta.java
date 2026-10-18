package co.edu.escuelaing.skycampus.service;

import java.util.List;
import java.util.Objects;

public class ServicioRuta {
    private final EstrategiaRuta estrategia;

    public ServicioRuta(EstrategiaRuta estrategia) {
        this.estrategia = Objects.requireNonNull(estrategia, "La estrategia de ruta es obligatoria");
    }

    public List<String> calcularRuta(String origen, String destino) {
        if (origen == null || origen.isBlank()) {
            throw new IllegalArgumentException("El origen es obligatorio");
        }
        if (destino == null || destino.isBlank()) {
            throw new IllegalArgumentException("El destino es obligatorio");
        }
        List<String> ruta = estrategia.calcularRuta(origen, destino);
        if (ruta == null || ruta.size() < 2
                || !origen.equals(ruta.get(0))
                || !destino.equals(ruta.get(ruta.size() - 1))
                || ruta.stream().anyMatch(punto -> punto == null || punto.isBlank())) {
            throw new IllegalStateException("La estrategia debe devolver una ruta válida entre origen y destino");
        }
        return List.copyOf(ruta);
    }
}
