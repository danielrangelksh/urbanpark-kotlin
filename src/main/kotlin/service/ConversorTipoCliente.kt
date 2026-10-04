package org.example.service

import org.example.model.TipoCliente
import org.example.model.TipoClienteInvalidoException

fun parsearTipoCliente(texto: String): TipoCliente = when(texto.trim().lowercase()) {
    "regular" -> TipoCliente.Regular
    "abonado" -> TipoCliente.Abonado
    "discapacitado" -> TipoCliente.Discapacitado
    else -> throw TipoClienteInvalidoException(texto)
}