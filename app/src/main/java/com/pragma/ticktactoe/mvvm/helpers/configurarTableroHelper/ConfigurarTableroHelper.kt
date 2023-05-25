package com.pragma.ticktactoe.mvvm.helpers.configurarTableroHelper

import com.pragma.ticktactoe.models.DetalleCasillaTriqui

interface ConfigurarTableroHelper {
    fun generarEstadoInicalTablero() : ConfigurarTableroHelper
    fun traerConfiguracionInicalTablero(): MutableList<DetalleCasillaTriqui>
}
