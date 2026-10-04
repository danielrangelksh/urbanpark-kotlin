package org.example

import org.example.model.Camioneta
import org.example.model.Moto
import org.example.model.TipoCliente
import org.example.model.UrbanParkException
import org.example.model.Vehiculo
import org.example.model.VehiculoParticular
import org.example.service.ConsultasService
import org.example.service.EstacionamientoService
import org.example.service.ReporteService
import org.example.service.parsearTipoCliente
import org.example.service.validarPatente
import java.time.LocalDateTime

suspend fun intentarEntrada(servicio: EstacionamientoService, vehiculo: Vehiculo){
    try{
        servicio.registrarEntrada(vehiculo)
    } catch (e: UrbanParkException){
        println("ERROR ${e.message}")
    }
}

suspend fun intentarSalida(servicio: EstacionamientoService, patente: String, minutos:Long){
    try{
        servicio.registrarSalida(patente, minutos)
    }catch (e: UrbanParkException){
        println("ERROR ${e.message}")
    }
}

suspend fun main(){
    val estacionamiento = EstacionamientoService("UrbanPark Centro", 10)
    val consultas = ConsultasService(estacionamiento)
    val reporte = ReporteService(consultas)
    val ahora = LocalDateTime.now()

   for (numero in 6..10){
       estacionamiento.inhabilitarEspacio(numero, "mantención")
   }

    val corolla = VehiculoParticular("AB12CD", "Toyota Corolla", ahora, TipoCliente.Abonado)
    val i10 = VehiculoParticular("XY99ZA", "Hyundai i10", ahora, TipoCliente.Regular)
    val yamaha = Moto("MO22TO", "Yamaha MT-07", ahora, TipoCliente.Regular)
    val ranger = Camioneta("CA44RG", "Ford Ranger", ahora, TipoCliente.Discapacitado, true)
    val dmax = Camioneta("CA77RG", "Chevrolet D-Max", ahora, TipoCliente.Regular, false)

    intentarEntrada(estacionamiento, corolla)
    intentarEntrada(estacionamiento, i10)
    intentarEntrada(estacionamiento, yamaha)
    intentarEntrada(estacionamiento, ranger)
    intentarEntrada(estacionamiento, dmax)

    println()
    reporte.mostrarEstadoEspacios()

    println("Errores controlados (el sistema sigue funcionando)")
    //patente invalida
    intentarEntrada(estacionamiento, Moto("123ABC", "Honda", ahora, TipoCliente.Regular))
    //estacionamiento sin capacidad
    intentarEntrada(estacionamiento, VehiculoParticular("ZZ11ZZ", "Kia Rio", ahora, TipoCliente.Regular))
    //Vehiculo no encontrado
    intentarSalida(estacionamiento, "QQ00QQ", 30)
    //resultado de tarifa invalida por tiempo estacionado igual a 0
    intentarSalida(estacionamiento, "AB12CD", 0)

    println("\nSALIDAS")
    intentarSalida(estacionamiento, "AB12CD", 75)
    intentarSalida(estacionamiento, "XY99ZA", 180)
    intentarSalida(estacionamiento, "MO22TO", 25)
    intentarSalida(estacionamiento, "CA44RG", 120)
    intentarSalida(estacionamiento, "CA77RG", 45)

    println("\n")
    reporte.mostrarConsultas()

    println("\n")
    reporte.mostrarReporteCierre()
}