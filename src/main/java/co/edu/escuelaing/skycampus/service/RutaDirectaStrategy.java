package co.edu.escuelaing.skycampus.service;

import java.util.List;

public class RutaDirectaStrategy implements EstrategiaRuta {
    @Override
    public List<String> calcularRuta(String origen, String destino) {
        return List.of(origen, destino);
    }
}
