# SkyCampus — Refuerzo DOSW

Proyecto MVP del sistema de reparto de drones descrito en
[DOSW_Equipo_Chimchar.html](./DOSW_Equipo_Chimchar.html).

## Requisitos

- JDK 17 o posterior.
- Maven 3.9 o posterior.

El HTML no fija una versión de Java. El proyecto usa Java 17 para conservar
compatibilidad amplia; el entorno actual tiene JDK 25, que puede compilar y
ejecutar este código.

## Estructura

```text
src/
├── main/java/co/edu/escuelaing/skycampus/
│   ├── domain/         # Drone y Mision
│   ├── enumeration/    # Estados de misión y tipos de carga
│   └── service/        # Reglas de validación de misión
└── test/java/          # Pruebas unitarias con JUnit 5
```

JUnit 5 y Mockito están configurados como dependencias de prueba. JaCoCo
genera el reporte de cobertura.

## Comandos

```bash
mvn test
mvn verify
```

El reporte HTML de JaCoCo queda en `target/site/jacoco/index.html`.
