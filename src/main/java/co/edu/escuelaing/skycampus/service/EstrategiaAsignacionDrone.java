package co.edu.escuelaing.skycampus.service;

import co.edu.escuelaing.skycampus.domain.Drone;

import java.util.List;

public interface EstrategiaAsignacionDrone {
    Drone seleccionar(List<Drone> drones);
}
