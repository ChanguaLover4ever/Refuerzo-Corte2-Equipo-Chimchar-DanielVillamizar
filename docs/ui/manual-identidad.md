# Manual de identidad visual — SkyCampus

## Propósito y alcance

Este manual establece una identidad visual mínima para SkyCampus y orienta la lectura de un panel de monitoreo de flota. La propuesta representa el modelo vigente del MVP: drones con identificador, modelo, batería, ubicación y uno de cuatro estados operacionales. El mockup es una vista estática basada en el conjunto de prueba de `ConsultasFlota`; no representa telemetría en tiempo real ni incorpora funcionalidades ajenas al alcance actual.

## Marca

**Nombre:** SkyCampus.

**Concepto:** plataforma de gestión y monitoreo de drones para el reparto de documentos dentro del campus.

El nombre combina la referencia al vuelo —*Sky*— con el entorno académico —*Campus*. La identidad transmite una operación ordenada, legible y confiable. En interfaz se escribe **SkyCampus**, con mayúsculas iniciales, sin abreviaturas ni variantes de nombre.

## Paleta cromática

| Función | Color | Aplicación y justificación |
|---|---|---|
| Primario | `#16324F` | Azul profundo para la marca, encabezados y jerarquía principal. Evoca confianza y ofrece alto contraste con superficies claras. |
| Secundario | `#526477` | Azul grisáceo para subtítulos y contenido auxiliar. Diferencia la información secundaria sin competir con los datos de la flota. |
| Acento | `#0F766E` | Verde azulado para detalles de identidad y elementos destacados. Aporta contraste cromático sin apropiarse de los colores asignados a los estados. |
| Fondo | `#F4F7FB` | Gris azulado muy claro para el lienzo general. Reduce el contraste visual excesivo y separa las tarjetas blancas del fondo. |
| Superficie | `#FFFFFF` | Blanco para tarjetas, tabla y alertas; facilita la lectura y organiza el contenido en bloques. |
| Texto principal | `#0F172A` | Azul casi negro para títulos, porcentajes y datos principales, con contraste elevado sobre superficies claras. |
| Divisor y borde | `#E2E8F0` | Gris frío claro para límites y separadores discretos, sin añadir ruido visual. |

Los colores de texto y estado se acompañan siempre de etiquetas visibles; el color por sí solo no comunica el significado.

## Colores operacionales

Cada estado se identifica con un indicador cromático, una etiqueta textual y un fondo suave. El uso conjunto evita depender exclusivamente de la percepción del color.

| Estado del dominio | Indicador | Fondo de etiqueta | Uso |
|---|---|---|---|
| Disponible | `#15803D` | `#DCFCE7` | Dron listo para ser considerado disponible. Verde comunica una condición operativa favorable. |
| En vuelo | `#2563EB` | `#DBEAFE` | Misión en curso según el estado del dron. Azul distingue la actividad de vuelo del estado disponible. |
| En carga | `#D97706` | `#FEF3C7` | Dron conectado a carga. Ámbar advierte que no está disponible para asignación sin sugerir una falla. |
| Fallo | `#DC2626` | `#FEE2E2` | Condición de fallo que requiere atención. Rojo destaca con claridad una situación operativa adversa. |

La relación con los valores del dominio es directa: `DISPONIBLE`, `EN_VUELO`, `EN_CARGA` y `FALLO`. Los nombres descriptivos en español aparecen en la interfaz.

## Tipografía

**Interfaz principal:** sans-serif, con la pila `Inter, Roboto, "Segoe UI", sans-serif`. Inter es la primera opción por su legibilidad en pantalla; Roboto y Segoe UI son alternativas comunes cuando Inter no está instalada. Los encabezados usan peso seminegrita y los datos de tabla conservan un tamaño legible y consistente.

**Elementos técnicos:** fuente monoespaciada —`Consolas, "Courier New", monospace`— para identificadores como `D-01` y códigos de misión. La anchura uniforme facilita distinguir y comparar cadenas técnicas sin afectar la lectura del texto general.

## Tono de comunicación

La interfaz se comunica de manera **clara, técnica y directa**. Los mensajes nombran el dato afectado y el criterio aplicable, evitan expresiones ambiguas y no afirman acciones que el sistema no ha completado. Se prefieren frases breves en voz activa.

Ejemplos coherentes con reglas y mensajes del MVP:

- **Notificación de asignación:** «Misión M-01 asignada al drone D-01».
- **Validación de batería:** «El drone debe tener al menos 30% de batería».
- **Validación de destino:** «El destino no existe en el campus».
- **Alerta del panel de referencia:** «D-04: batería crítica de consulta (18 %; umbral inferior a 20 %)».

La alerta de batería del mockup representa el criterio de la consulta de flota que cuenta niveles inferiores al 20 %. No reemplaza ni modifica las validaciones de asignación de misión del dominio.

## Criterios de aplicación

1. Mantener una jerarquía visual estable: identidad y título, resumen, tabla de flota y alertas.
2. Repetir los mismos colores, etiquetas y tratamientos para un mismo estado en todos los componentes del panel.
3. Mostrar la batería como porcentaje numérico junto a su indicador visual.
4. Usar identificadores monoespaciados y conservar los valores del dominio sin reinterpretarlos.
5. Mantener las superficies despejadas, el contraste suficiente y la información relevante visible en una sola vista.
