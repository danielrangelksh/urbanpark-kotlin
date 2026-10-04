package org.example.model

sealed class TipoCliente(val etiqueta: String) {
    object Regular: TipoCliente("regular")
    object Abonado: TipoCliente("abonado")
    object Discapacitado: TipoCliente("discapacitado")
}