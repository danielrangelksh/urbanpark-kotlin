package org.example.model

open class UrbanParkException(mensaje: String):
        Exception(mensaje)

open class PatenteInvalidaException(patente: String):
        UrbanParkException("Patente inálida '${patente}'. Formato esperado: dos letras, dos digitos, dos letras (Ejemplo: AB12CD).")

class TipoClienteInvalidoException(valor: String):
        UrbanParkException("Tipo cliente inválido '${valor}'. Los valores permitidos son: regular, abonado, discapacitado.")

class TarifaInvalidaException(detalle: String):
        UrbanParkException("Error de cálculo: '$detalle'.")

class VehiculoNoEncontradoException(patente:String):
        UrbanParkException("No se encontró vehículo en el estacionamiento con patente: '$patente'.")

class EstacionamientoLlenoException :
        UrbanParkException("Estacionamento en lleno!")


