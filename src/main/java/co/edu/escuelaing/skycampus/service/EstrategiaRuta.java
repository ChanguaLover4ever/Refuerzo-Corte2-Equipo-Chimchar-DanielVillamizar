package co.edu.escuelaing.skycampus.service;

import java.util.List;

@FunctionalInterface
public interface EstrategiaRuta {
    List<String> calcularRuta(String origen, String destino);
}
