package org.example.service

import kotlinx.coroutines.delay
import org.example.model.Espacio
import org.example.model.EstacionamientoLlenoException
import org.example.model.EstadoEspacio
import org.example.model.Ticket
import org.example.model.Vehiculo
import org.example.model.VehiculoNoEncontradoException

class EstacionamientoService(val nombre: String, val capacidad: Int) {
    val espacios: List<Espacio> = List(capacidad) { indice -> Espacio(indice+1)}

    val historial: MutableList<Ticket> = mutableListOf()

    fun puedeRecibirVehiculo(espacio: Espacio): Boolean = when (espacio.estado) {
        is EstadoEspacio.FueraDeServicio -> false
        is EstadoEspacio.Libre -> true
        is EstadoEspacio.Ocupado -> false
        is EstadoEspacio.Procesando -> false
    }

    fun vehiculoEn(espacio: Espacio): Vehiculo? = when(val estado = espacio.estado) {
        is EstadoEspacio.Ocupado -> estado.vehiculo
        is EstadoEspacio.Libre -> null
        is EstadoEspacio.FueraDeServicio -> null
        is EstadoEspacio.Procesando -> null
    }

    fun inhabilitarEspacio(numero: Int, motivo: String){
        val espacio = espacios.firstOrNull { it.numero == numero }
        if (espacio != null && espacio.estaLibre()){
            espacio.estado = EstadoEspacio.FueraDeServicio(motivo)
        }
    }

    suspend fun registrarEntrada(vehiculo: Vehiculo){
        validarPatente(vehiculo.patente)
        val espacio = espacios.firstOrNull{puedeRecibirVehiculo(it)}?: throw EstacionamientoLlenoException()

        espacio.estado = EstadoEspacio.Procesando("Registrando entrada de ${vehiculo.patente}")
        println("   ${espacio.detalle()}")

        delay(3000)

        espacio.estado = EstadoEspacio.Ocupado(vehiculo)
        println("Entrada registrada: ${vehiculo.detalle()} -> espacio ${espacio.numero}")
    }

    suspend fun registrarSalida(patente: String, minutos: Long): Ticket {
        val vehiculo = espacios.mapNotNull { vehiculoEn(it) }.firstOrNull() { it.patente == patente }
            ?: throw VehiculoNoEncontradoException(patente)

        val espacio = espacios.first { vehiculoEn(it) === vehiculo }

        val monto = vehiculo.calcularMontoTotal(minutos)

        espacio.estado = EstadoEspacio.Procesando("calculando tarifa de $patente")
        println("   ${espacio.detalle()}")

        delay(6500)

        val ticket = Ticket(historial.size + 1, vehiculo, minutos, monto)
        historial.add(ticket)
        espacio.estado = EstadoEspacio.Libre
        println("Salida registrada: ${ticket.detalle()} (espacio ${espacio.numero}) liberado")
        return ticket
    }
}