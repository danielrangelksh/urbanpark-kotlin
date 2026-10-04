# UrbanPark

Sistema de consola en Kotlin para gestionar un estacionamiento de 10 espacios.
Evaluación Parcial 1 de DSY1105 (Desarrollo de Aplicaciones Móviles): fundamentos de Kotlin y POO.

## Qué hace

- Calcula tarifas según el tipo de vehículo (particular, moto, camioneta) y de cliente (regular, abonado, discapacitado), con IVA 19%.
- Controla el estado de cada espacio: libre, ocupado, procesando o fuera de servicio.
- Simula entradas (3 s) y salidas (6,5 s) con corrutinas (`suspend fun` y `delay`).
- Maneja errores con excepciones propias y `try-catch`: patente inválida, estacionamiento lleno, vehículo no encontrado y tarifa inválida.
- Responde consultas de negocio y muestra un reporte de cierre de turno.

## Estructura

```
src/main/kotlin/
├── Main.kt
├── model/     Vehiculo, VehiculoParticular, Moto, Camioneta, TipoCliente,
│              EstadoEspacio, Espacio, Ticket, Excepciones
└── service/   EstacionamientoService, ConsultasService, ReporteService,
               ValidadorPatente, ConversorTipoCliente
```

## Cómo ejecutarlo

1. Abrir el proyecto en IntelliJ IDEA.
2. Verificar que `build.gradle.kts` incluya `org.jetbrains.kotlinx:kotlinx-coroutines-core`.
3. Ejecutar `main` en `Main.kt`.
4. La ejecución completa tarda cerca de 48 segundos por las esperas simuladas.
