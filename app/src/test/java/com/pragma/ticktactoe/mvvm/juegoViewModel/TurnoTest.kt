package com.pragma.ticktactoe.mvvm.juegoViewModel

import com.pragma.ticktactoe.constantes.CasillasTableroEnum
import com.pragma.ticktactoe.constantes.EstadoJuegoEnum
import com.pragma.ticktactoe.constantes.JugadorCasillaEnum
import com.pragma.ticktactoe.models.DetalleCasillaTriqui
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.verify
import org.junit.Assert
import org.junit.Test

class TurnoTest : BaseJuegoViewModelTest() {

    @Test
    fun primerTurno() {
        //Preconfiguracion
        val tablero = traerTablero()
        val casillaAActualizar = DetalleCasillaTriqui().apply { jugadorCasillaActual = JugadorCasillaEnum.JUGADOR1 }
        val tableroActualizado = emptyList<DetalleCasillaTriqui>().toMutableList()

        tableroActualizado.addAll(tablero)
        tableroActualizado[tablero.indexOf(tableroActualizado.find { cas -> cas.casillaActual == CasillasTableroEnum.CASILLA_0_0 })] = casillaAActualizar

        //Given
        coEvery { actualizarTableroHelper.actualizarTablero(casillasTablero = any(), detalleCasillaTriqui = any()) }.returns(tableroActualizado)
        coEvery { finalizoJuegoHelper.hayGanador() } returns false

        //When
        juegoViewModel.reiniciarJuego()
        juegoViewModel.turno(estadoJuegoEnum = EstadoJuegoEnum.TURNO_JUGADOR1, detalleCasillaTriqui =  casillaAActualizar)

        //Then
        Assert.assertEquals(tableroActualizado, juegoViewModel.estadoActualTablero().value)
        Assert.assertEquals(EstadoJuegoEnum.TURNO_JUGADOR2, juegoViewModel.turnoActual().value)
        verify(exactly = 1) { actualizarTableroHelper.actualizarTablero(casillasTablero = any(), detalleCasillaTriqui = any()) }
        verify(exactly = 1) { finalizoJuegoHelper.validarGanador(casillas = any()) }
        verify(exactly = 1) { finalizoJuegoHelper.hayGanador()}
    }

}