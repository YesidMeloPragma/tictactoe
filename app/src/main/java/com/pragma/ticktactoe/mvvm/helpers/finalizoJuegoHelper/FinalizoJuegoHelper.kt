package com.pragma.ticktactoe.mvvm.helpers.finalizoJuegoHelper

import com.pragma.ticktactoe.constantes.CasillasTableroEnum
import com.pragma.ticktactoe.constantes.JugadorCasillaEnum
import com.pragma.ticktactoe.models.DetalleCasillaTriqui

interface FinalizoJuegoHelper {
    fun traerGanador(): JugadorCasillaEnum
    fun hayGanador(): Boolean
    fun validarGanador(casillas: List<DetalleCasillaTriqui>)
}
