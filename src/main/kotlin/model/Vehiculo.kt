package org.example.model

import java.time.LocalDateTime
sealed class Vehiculo (
    val patente: String,
    val marca: String,
    val fechaIngreso: LocalDateTime,
    val tipoCliente: TipoCliente
) {
    abstract val tipoVehiculo: String
    abstract val tarifaBaseHora: Double

    abstract fun calcularCostoBase(minutos: Long): Double
    fun costoPorTiempo(minutos: Long): Double = tarifaBaseHora * minutos / 60.0

    open fun esCobroGratuito(minutos: Long): Boolean = false

    open fun detalle(): String = "$tipoVehiculo | Patente: $patente | Marca: $marca | Cliente: ${tipoCliente.etiqueta}"

    fun calcularMontoTotal(minutos: Long): Double {
        val iva = 0.19
        val descuentoDiscapacitado = 0.50

        if (minutos <= 0) {
            throw TarifaInvalidaException("El timepo estacionado debe ser mayor a cero")
        }

        if (esCobroGratuito(minutos)) return 0.0

        val costoBase = calcularCostoBase(minutos)
        val conIva = costoBase * (1 + iva)
        val total = if (tipoCliente == TipoCliente.Discapacitado) {
            conIva * (1 - descuentoDiscapacitado)
        } else {
            conIva
        }

        if (total <= 0.0){
            throw TarifaInvalidaException("El monto calculado para la patente $patente es $total.")
        }
        return total
    }
}
