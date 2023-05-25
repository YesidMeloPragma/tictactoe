package com.pragma.ticktactoe.mvvm.helpers.finalizoJuegoHelper

import com.pragma.ticktactoe.constantes.CasillasTableroEnum
import com.pragma.ticktactoe.constantes.JugadorCasillaEnum
import com.pragma.ticktactoe.models.DetalleCasillaTriqui

interface FinalizoJuegoHelper {
    fun hayGanador(): Boolean
    fun reiniciar()
    fun traerGanador(): JugadorCasillaEnum
    fun validarGanador(casillas: List<DetalleCasillaTriqui>)
}
