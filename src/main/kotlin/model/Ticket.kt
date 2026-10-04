package org.example.model

class Ticket(
    val numero: Int,
    val vehiculo: Vehiculo,
    val minutosEstacionado: Long,
    val montoPagado: Double
) {
    fun detalle(): String= "Ticket $numero | ${vehiculo.tipoVehiculo} | ${vehiculo.patente} | " +
            "$minutosEstacionado min | $${"%.2f".format(montoPagado)}"
}