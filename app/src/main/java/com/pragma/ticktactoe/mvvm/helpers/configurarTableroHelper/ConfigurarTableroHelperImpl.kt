package com.pragma.ticktactoe.mvvm.helpers.configurarTableroHelper

import com.pragma.ticktactoe.constantes.CasillasTableroEnum
import com.pragma.ticktactoe.models.DetalleCasillaTriqui


class ConfigurarTableroHelperImpl : ConfigurarTableroHelper {
    private val configuracionInicial = emptyList<DetalleCasillaTriqui>().toMutableList()

    override fun generarEstadoInicalTablero() : ConfigurarTableroHelper {
        configuracionInicial.clear()
        CasillasTableroEnum.values().forEach {
            configuracionInicial.add(generarCasilla(casillaActual = it))
        }
        return this
    }

    override fun traerConfiguracionInicalTablero() = configuracionInicial

    private fun generarCasilla(
        casillaActual: CasillasTableroEnum
    ) : DetalleCasillaTriqui {
        return DetalleCasillaTriqui().apply {
            this.casillaActual = casillaActual
        }
    }
}