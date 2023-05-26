package com.pragma.ticktactoe.mvvm.configurarTableroHelper

import org.junit.Test

class generarEstadoInicalTableroTest : BaseConfigurarTableroHelperTest(){

    @Test
    fun generacionExitosa() {
        //Given
        //When
        configurarTableroHelper.generarEstadoInicalTablero()
        //Then
        assert(configurarTableroHelper.traerConfiguracionInicalTablero().isNotEmpty())
    }
}