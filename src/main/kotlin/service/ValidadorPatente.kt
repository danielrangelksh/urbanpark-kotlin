package org.example.service

import org.example.model.PatenteInvalidaException

fun esPatenteValida(patente: String): Boolean{
    if (patente.length != 6) return false

    val letrasInicio = patente[0] in 'A'..'Z' && patente [1] in 'A'..'Z'
    val digitos = patente[2] in '0'..'9' && patente [3] in '0'..'9'
    val letrasFin = patente[4] in 'A'..'Z' && patente [5] in 'A'..'Z'

    return letrasInicio && digitos && letrasFin
}

fun validarPatente(patente: String){
    if (!esPatenteValida(patente)) throw PatenteInvalidaException(patente)
}