package org.example.model

import java.time.LocalDateTime

class Moto(
    patente: String,
    marca: String,
    fechaIngreso: LocalDateTime,
    tipoCliente: TipoCliente
) : Vehiculo(patente, marca, fechaIngreso, tipoCliente) {

    override val tipoVehiculo = "Moto"
    override val tarifaBaseHora = 800.0

    override fun calcularCostoBase(minutos: Long): Double = costoPorTiempo(minutos)

    override fun esCobroGratuito(minutos: Long): Boolean {
        val minutosGratis = 30
        return minutos < minutosGratis
    }
}