package org.example.model

class Espacio (
    val numero: Int,
    var estado: EstadoEspacio = EstadoEspacio.Libre
){
    fun estaLibre(): Boolean = estado is EstadoEspacio.Libre

    fun detalle(): String ="Espacio $numero: ${estado.descripcion()}"
}