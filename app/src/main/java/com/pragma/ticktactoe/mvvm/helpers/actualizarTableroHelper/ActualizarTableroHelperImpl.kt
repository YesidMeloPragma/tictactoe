package com.pragma.ticktactoe.mvvm.helpers.actualizarTableroHelper

import com.pragma.ticktactoe.models.DetalleCasillaTriqui

class ActualizarTableroHelperImpl : ActualizarTableroHelper {

    override fun actualizarTablero(
        casillasTablero: MutableList<DetalleCasillaTriqui>,
        detalleCasillaTriqui: DetalleCasillaTriqui
    ) : MutableList<DetalleCasillaTriqui> {

        val tableroActualizado = emptyList<DetalleCasillaTriqui>().toMutableList()
        tableroActualizado.addAll(casillasTablero)

        val casilla = tableroActualizado.find { item -> item.casillaActual == detalleCasillaTriqui.casillaActual }
        tableroActualizado[tableroActualizado.indexOf(casilla)] = detalleCasillaTriqui

        return tableroActualizado
    }
}