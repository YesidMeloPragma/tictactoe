package com.pragma.ticktactoe.mvvm.actualizarTableroHelper

import com.pragma.ticktactoe.constantes.CasillasTableroEnum
import com.pragma.ticktactoe.constantes.JugadorCasillaEnum
import com.pragma.ticktactoe.models.DetalleCasillaTriqui
import org.junit.Test

class ActualizarTableroTest : BaseActualizarTableroHelperTest() {

    @Test
    fun actualizarCasilla() {
        //Given
        val listaTablero = traerTablero()

        //When
        val casillaAActualizar = DetalleCasillaTriqui().apply {
            jugadorCasillaActual = JugadorCasillaEnum.JUGADOR1
            casillaActual = CasillasTableroEnum.CASILLA_1_1
        }

        val tablero = actualizarTableroHelper.actualizarTablero(casillasTablero = listaTablero, detalleCasillaTriqui = casillaAActualizar)

        //then
        val casillaTablero = tablero.find { casilla -> casilla.casillaActual == CasillasTableroEnum.CASILLA_1_1 }
        assert(casillaAActualizar.jugadorCasillaActual == casillaTablero!!.jugadorCasillaActual)
    }

}