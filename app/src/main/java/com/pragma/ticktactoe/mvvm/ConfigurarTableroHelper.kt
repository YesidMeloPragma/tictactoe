package com.pragma.ticktactoe.mvvm

import com.pragma.ticktactoe.constantes.CasillasTableroEnum
import com.pragma.ticktactoe.models.DetalleCasillaTriqui

class ConfigurarTableroHelper {
    private val configuracionInicial = emptyList<DetalleCasillaTriqui>().toMutableList()

    fun generarEstadoInicalTablero() : ConfigurarTableroHelper {
        configuracionInicial.clear()
        CasillasTableroEnum.values().forEach {
            configuracionInicial.add(generarCasilla(casillaActual = it))
        }
        return this
    }

    fun traerConfiguracionInicalTablero() = configuracionInicial

    private fun generarCasilla(
        casillaActual: CasillasTableroEnum
    ) : DetalleCasillaTriqui {
        return DetalleCasillaTriqui().apply {
            this.casillaActual = casillaActual
        }
    }
}