package org.example.model

import java.time.LocalDateTime

class Camioneta(
    patente: String,
    marca: String,
    fechaIngreso: LocalDateTime,
    tipoCliente: TipoCliente,
    val esCargaPesada: Boolean
) : Vehiculo(patente, marca, fechaIngreso, tipoCliente) {

    override val tipoVehiculo = "Camioneta"
    override val tarifaBaseHora = 2500.0

    override fun calcularCostoBase(minutos: Long): Double{
        val recargoCargaPesada = 0.30
        val costo = costoPorTiempo(minutos)
        return if (esCargaPesada) costo * (1+recargoCargaPesada) else costo
    }

    override fun detalle(): String =
        super.detalle() + " | Carga pesada: ${if (esCargaPesada) "Sí" else "No"}"

}