package org.example.service

import org.example.model.Ticket
import org.example.model.TipoCliente
import org.example.model.Vehiculo

class ConsultasService(val estacionamiento: EstacionamientoService) {
    fun espaciosDisponibles(): Int = estacionamiento.espacios.count{it.estaLibre()}

    fun vehiculosAbonados(): List<Vehiculo> = estacionamiento.historial
        .map{it.vehiculo}. filter{it.tipoCliente == TipoCliente.Abonado}

    fun recaudacionTotal(): Double = estacionamiento.historial.sumOf{it.montoPagado}

    fun ingresoPromedio(): Double{
        val atendidos = estacionamiento.historial.size

        return if (atendidos == 0) 0.0 else recaudacionTotal() / atendidos
    }

    fun patentesSalidas(): List<String> = estacionamiento.historial.map{it.vehiculo.patente}

    fun ticketMasTiempo(): Ticket? = estacionamiento.historial.maxByOrNull{it.minutosEstacionado}

    fun recaudacionPorTipo(): Map<String, Double> = estacionamiento.historial.groupBy{it.vehiculo.tipoVehiculo}
        .mapValues{grupo -> grupo.value.sumOf{it.montoPagado}}

    fun tipoMasIngresos(): String? = recaudacionPorTipo().maxByOrNull { it.value }?.key
}
