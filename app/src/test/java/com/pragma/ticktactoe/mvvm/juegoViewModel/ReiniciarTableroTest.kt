package com.pragma.ticktactoe.mvvm.juegoViewModel

import com.pragma.ticktactoe.constantes.EstadoJuegoEnum
import io.mockk.coEvery
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ReiniciarTableroTest : BaseJuegoViewModelTest() {

    @Test
    fun configuracionInicialTablero() = runTest {
        //Given
        val listDetalleCasillaTriqui = traerTablero()

        coEvery { configurarTableroHelper.generarEstadoInicalTablero() } returns configurarTableroHelper
        coEvery { configurarTableroHelper.traerConfiguracionInicalTablero() } returns listDetalleCasillaTriqui

        //When
        juegoViewModel.reiniciarJuego()

        //then
        assert(juegoViewModel.estadoActualTablero().value == listDetalleCasillaTriqui)
        assert(juegoViewModel.turnoActual().value == EstadoJuegoEnum.TURNO_JUGADOR1)

    }

}