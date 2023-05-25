package com.pragma.ticktactoe.mvvm.JuegoViewModel

import com.pragma.ticktactoe.constantes.CasillasTableroEnum
import com.pragma.ticktactoe.constantes.EstadoJuegoEnum
import com.pragma.ticktactoe.constantes.JugadorCasillaEnum
import com.pragma.ticktactoe.constantes.ValorCasillaEnum
import com.pragma.ticktactoe.models.DetalleCasillaTriqui
import io.mockk.coEvery
import org.junit.Test

class TurnoTest : BaseJuegoViewModelTest() {

    @Test
    fun primerTurno(){
        //Given
        val listDetalleCasillaTriqui = traerTablero()

        coEvery { configurarTableroHelper.generarEstadoInicalTablero() } returns configurarTableroHelper
        coEvery { configurarTableroHelper.traerConfiguracionInicalTablero() } returns listDetalleCasillaTriqui

        //When
        juegoViewModel.reiniciarJuego()
        juegoViewModel.turno(estadoJuegoEnum = EstadoJuegoEnum.TURNO_JUGADOR1, detalleCasillaTriqui = DetalleCasillaTriqui().apply {
            this.casillaActual = CasillasTableroEnum.CASILLA_0_0
            this.jugadorCasillaActual = JugadorCasillaEnum.JUGADOR1
            this.valorCasillaActual = ValorCasillaEnum.O
        })

        //then
        assert(juegoViewModel.estadoActualTablero().value == listDetalleCasillaTriqui)
        assert(juegoViewModel.turnoActual().value == EstadoJuegoEnum.TURNO_JUGADOR2)
    }
}