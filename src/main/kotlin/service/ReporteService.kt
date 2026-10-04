package org.example.service

class ReporteService(
    val consultas: ConsultasService
) {
    fun mostrarEstadoEspacios(){
        println("Estado de los espacios:")
        for (espacio in consultas.estacionamiento.espacios){
            println(espacio.detalle())
        }
    }

    fun mostrarConsultas(){
        println("Consultas de negocio:")
        println("1. Espacios disponibles ahora: ${consultas.espaciosDisponibles()}")
        println("2. Vehiculos abonados del historial:")
        val abonados = consultas.vehiculosAbonados()
        if (abonados.isEmpty()) println("   (ninguno)")
        abonados.forEach{println("  -${it.detalle()}")}
        println("3. Ingreso promedio por vehiculo: $${"%.2f".format(consultas.ingresoPromedio())}")
        println("4. Patentes que han salido: ${consultas.patentesSalidas().joinToString(", ")}")
        val masTiempo = consultas.ticketMasTiempo()
        if (masTiempo != null){
            println("5. Vehiculo con más tiempo: ${masTiempo.vehiculo.patente} (${masTiempo.minutosEstacionado} min)")
        }else{
            println("5. Vehiculo con más tiempo: (sin datos)")
        }
        println("recaudación por tipo de vehiculo:")
        for (entrada in consultas.recaudacionPorTipo()){
            println("   ${entrada.key}: $${"%.2f".format(entrada.value)}")
        }
    }

    fun mostrarReporteCierre(){
        val estacionamiento = consultas.estacionamiento
        println("Reporte de cierre de turno - ${estacionamiento.nombre}")

        for (ticket in estacionamiento.historial){
            println(ticket.detalle())
        }

        println("Total recaudado: $${"%.2f".format(consultas.recaudacionTotal())}")
        println("Vehículos atendidos: ${estacionamiento.historial.size}")
        println("Ingreso promedio: $${"%.2f".format(consultas.ingresoPromedio())}")
        println("Tipo con más ingresos: ${consultas.tipoMasIngresos() ?: "sin datos"}")
        println("Espacios disponibles al cierre: ${consultas.espaciosDisponibles()}")
    }
}