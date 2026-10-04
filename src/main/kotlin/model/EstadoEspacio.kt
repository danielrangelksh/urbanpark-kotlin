package org.example.model

sealed class EstadoEspacio {
    object Libre : EstadoEspacio()

    class Ocupado (val vehiculo: Vehiculo) : EstadoEspacio()

    class Procesando(val motivo: String): EstadoEspacio()

    class FueraDeServicio(val motivo: String): EstadoEspacio()

    fun descripcion(): String = when(this){
        is Libre -> "Libre"
        is Ocupado -> "Ocupado por ${vehiculo.patente}(${vehiculo.tipoVehiculo}"
        is FueraDeServicio -> "Fuera De servicio: ${motivo}"
        is Procesando -> "Procesando: $motivo"
    }
}