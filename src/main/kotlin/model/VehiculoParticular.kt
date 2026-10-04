package org.example.model

import java.time.LocalDateTime

class VehiculoParticular(
    patente: String,
    marca: String,
    fechaIngreso: LocalDateTime,
    tipoCliente: TipoCliente
    ): Vehiculo(patente, marca, fechaIngreso, tipoCliente) {

    override val tipoVehiculo = "Particular"
    override val tarifaBaseHora = 1500.0

    override fun calcularCostoBase(minutos: Long): Double{
        val descuentoAbonado = 0.20
        val costo = costoPorTiempo(minutos)
        return if (tipoCliente == TipoCliente.Abonado)
            costo * (1 - descuentoAbonado) else costo
    }
}