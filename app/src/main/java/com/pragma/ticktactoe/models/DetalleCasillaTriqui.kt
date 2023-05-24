package com.pragma.ticktactoe.models

import com.pragma.ticktactoe.constantes.CasillasTableroEnum
import com.pragma.ticktactoe.constantes.JugadorCasillaEnum
import com.pragma.ticktactoe.constantes.ValorCasillaEnum

class DetalleCasillaTriqui {
    var casillaActual: CasillasTableroEnum = CasillasTableroEnum.CASILLA_0_0
    var jugadorCasillaActual : JugadorCasillaEnum = JugadorCasillaEnum.NINGUNO
    var valorCasillaActual: ValorCasillaEnum = ValorCasillaEnum.NINGUNO
}