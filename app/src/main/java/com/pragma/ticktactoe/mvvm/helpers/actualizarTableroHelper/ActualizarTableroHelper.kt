package com.pragma.ticktactoe.mvvm.helpers.actualizarTableroHelper

import com.pragma.ticktactoe.models.DetalleCasillaTriqui

interface ActualizarTableroHelper {

    fun actualizarTablero(
        casillasTablero : MutableList<DetalleCasillaTriqui>,
        detalleCasillaTriqui: DetalleCasillaTriqui
    ): MutableList<DetalleCasillaTriqui>

}