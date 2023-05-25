package com.pragma.ticktactoe.mvvm.finalizoJuego

import com.pragma.ticktactoe.constantes.CasillasTableroEnum
import com.pragma.ticktactoe.constantes.JugadorCasillaEnum
import com.pragma.ticktactoe.constantes.ValorCasillaEnum
import com.pragma.ticktactoe.models.DetalleCasillaTriqui
import org.junit.Test

class FinalizoJuegoGanadorFila : BaseFinalizoJuegoHelperTest() {

    @Test
    fun ganadorJuegoFila0() {
        val fila1 = generarCasillasFila(fila= 0)
        finalizoJuegoHelper.validarGanador(casillas = fila1)
        assert(finalizoJuegoHelper.hayGanador())
    }

    @Test
    fun ganadorJuegoFila1() {
        val fila1 = generarCasillasFila(fila= 1)
        finalizoJuegoHelper.validarGanador(casillas = fila1)
        assert(finalizoJuegoHelper.hayGanador())
    }

    @Test
    fun ganadorJuegoFila2() {
        val fila1 = generarCasillasFila(fila= 2)
        finalizoJuegoHelper.validarGanador(casillas = fila1)
        assert(finalizoJuegoHelper.hayGanador())
    }

    @Test
    fun ganadorJuegoColumna0() {
        val fila1 = generarCasillasColumna(columna = 0)
        finalizoJuegoHelper.validarGanador(casillas = fila1)
        assert(finalizoJuegoHelper.hayGanador())
    }


    @Test
    fun ganadorJuegoColumna1() {
        val fila1 = generarCasillasColumna(columna = 1)
        finalizoJuegoHelper.validarGanador(casillas = fila1)
        assert(finalizoJuegoHelper.hayGanador())
    }

    @Test
    fun ganadorJuegoColumna2() {
        val fila1 = generarCasillasColumna(columna = 2)
        finalizoJuegoHelper.validarGanador(casillas = fila1)
        assert(finalizoJuegoHelper.hayGanador())
    }

    @Test
    fun ganadorJuegoDiagonalIzquierda() {
        val diagonal = generarDiagonalIzquierda()
        finalizoJuegoHelper.validarGanador(casillas = diagonal)
        assert(finalizoJuegoHelper.hayGanador())
    }

    @Test
    fun ganadorJuegoDiagonalDerecha() {
        val diagonal = generarDiagonalDerecha()
        finalizoJuegoHelper.validarGanador(casillas = diagonal)
        assert(finalizoJuegoHelper.hayGanador())
    }

    private fun generarCasillasFila(fila : Int) :  MutableList<DetalleCasillaTriqui> {
        val lista = emptyList<DetalleCasillaTriqui>().toMutableList()
        for (casilla in CasillasTableroEnum.values()) {

            if (casilla.traerFila() != fila) {
                lista.add(DetalleCasillaTriqui().apply { this.casillaActual = casilla })
                continue
            }
            lista.add(DetalleCasillaTriqui().apply {
                this.casillaActual = casilla
                this.jugadorCasillaActual = JugadorCasillaEnum.JUGADOR1
                this.valorCasillaActual = ValorCasillaEnum.O
            })
        }
        return lista
    }

    private fun generarCasillasColumna(columna : Int) :  MutableList<DetalleCasillaTriqui> {
        val lista = emptyList<DetalleCasillaTriqui>().toMutableList()
        for (casilla in CasillasTableroEnum.values()) {

            if (casilla.traerColumna() != columna) {
                lista.add(DetalleCasillaTriqui().apply { this.casillaActual = casilla })
                continue
            }
            lista.add(DetalleCasillaTriqui().apply {
                this.casillaActual = casilla
                this.jugadorCasillaActual = JugadorCasillaEnum.JUGADOR1
                this.valorCasillaActual = ValorCasillaEnum.O
            })
        }
        return lista
    }

    private fun generarDiagonalIzquierda() : MutableList<DetalleCasillaTriqui> {
        val lista = emptyList<DetalleCasillaTriqui>().toMutableList()
        for (casilla in CasillasTableroEnum.values()) {
            if(casilla.traerFila() != casilla.traerColumna()) {
                lista.add(DetalleCasillaTriqui().apply { this.casillaActual = casilla })
                continue
            }
            lista.add(DetalleCasillaTriqui().apply {
                this.casillaActual = casilla
                this.jugadorCasillaActual = JugadorCasillaEnum.JUGADOR1
                this.valorCasillaActual = ValorCasillaEnum.O
            })
        }
        return lista
    }

    private fun generarDiagonalDerecha() : MutableList<DetalleCasillaTriqui> {
        val lista = emptyList<DetalleCasillaTriqui>().toMutableList()
        lista.add(DetalleCasillaTriqui().apply {
            this.casillaActual = CasillasTableroEnum.CASILLA_0_2
            this.jugadorCasillaActual = JugadorCasillaEnum.JUGADOR1
            this.valorCasillaActual = ValorCasillaEnum.O
        })

        lista.add(DetalleCasillaTriqui().apply {
            this.casillaActual = CasillasTableroEnum.CASILLA_1_1
            this.jugadorCasillaActual = JugadorCasillaEnum.JUGADOR1
            this.valorCasillaActual = ValorCasillaEnum.O
        })

        lista.add(DetalleCasillaTriqui().apply {
            this.casillaActual = CasillasTableroEnum.CASILLA_2_0
            this.jugadorCasillaActual = JugadorCasillaEnum.JUGADOR1
            this.valorCasillaActual = ValorCasillaEnum.O
        })
        for (casilla in CasillasTableroEnum.values()) {
            if (casilla == CasillasTableroEnum.CASILLA_0_2 ||
                casilla == CasillasTableroEnum.CASILLA_1_1 ||
                casilla == CasillasTableroEnum.CASILLA_2_0 ) continue

            lista.add(DetalleCasillaTriqui().apply { this.casillaActual = casilla })
        }
        return lista
    }
}