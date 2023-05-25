package com.pragma.ticktactoe.mvvm

import com.pragma.ticktactoe.constantes.CasillasTableroEnum
import com.pragma.ticktactoe.constantes.JugadorCasillaEnum
import com.pragma.ticktactoe.models.DetalleCasillaTriqui

interface FinalizoJuegoHelper {
    fun traerGanador(): JugadorCasillaEnum
    fun hayGanador(): Boolean
    fun validarGanador(casillas: List<DetalleCasillaTriqui>)
}

class FinalizoJuegoHelperImpl : FinalizoJuegoHelper {

    private var ganador = JugadorCasillaEnum.NINGUNO
    private var hayGanador = false
    private val numeroMaximoCasillas = 3

    override fun traerGanador(): JugadorCasillaEnum = ganador

    override fun hayGanador(): Boolean = hayGanador

    override fun validarGanador(casillas: List<DetalleCasillaTriqui>) {
        if (hayGanadorHorizontal(casillas = casillas)) return
        if(hayGanadorVertical(casillas = casillas)) return
        if(hayGanadorDiagonalIzquierda(casillas = casillas)) return
        hayGanadorDiagonalDerecha(casillas = casillas)
    }

    private fun hayGanadorHorizontal(casillas: List<DetalleCasillaTriqui>) : Boolean{
        for (numeroFila in 0 until numeroMaximoCasillas) {
            val fila = casillas.filter { casilla -> casilla.casillaActual.traerFila() == numeroFila && casilla.jugadorCasillaActual != JugadorCasillaEnum.NINGUNO }
            if (fila.size < numeroMaximoCasillas) continue

            val jugadorEnCasilla = fila.first().jugadorCasillaActual

            val ganadorfila = fila.filter { itemFila -> itemFila.jugadorCasillaActual == jugadorEnCasilla }
            if (ganadorfila.size < numeroMaximoCasillas) continue
            ganador = jugadorEnCasilla
            hayGanador = true
            return true
        }
        return false
    }

    private fun hayGanadorVertical(casillas: List<DetalleCasillaTriqui>) : Boolean {
        for (numeroFila in 0 until numeroMaximoCasillas) {
            val columna = casillas.filter { casilla -> casilla.casillaActual.traerColumna() == numeroFila && casilla.jugadorCasillaActual != JugadorCasillaEnum.NINGUNO }
            if (columna.size < numeroMaximoCasillas) continue

            val jugadorEnCasilla = columna.first().jugadorCasillaActual

            val ganadorfila = columna.filter { itemColumna -> itemColumna.jugadorCasillaActual == jugadorEnCasilla }
            if (ganadorfila.size < numeroMaximoCasillas) continue
            ganador = jugadorEnCasilla
            hayGanador = true
            return true
        }
        return false
    }

    private fun hayGanadorDiagonalIzquierda(casillas: List<DetalleCasillaTriqui>) : Boolean {
        val diagonal = casillas.filter { casilla -> casilla.casillaActual.traerFila() == casilla.casillaActual.traerColumna() }
        val jugador = diagonal.filter { casilla -> casilla.jugadorCasillaActual != JugadorCasillaEnum.NINGUNO }
        if (jugador.size < 3) return false

        val jugadorActual = jugador.first().jugadorCasillaActual
        val ganador = jugador.filter { item -> item.jugadorCasillaActual == jugadorActual }
        if (ganador.size < 3) return false
        hayGanador = true
        this.ganador =  jugadorActual
        return true
    }

    private fun hayGanadorDiagonalDerecha(casillas: List<DetalleCasillaTriqui>) : Boolean {
        val diagonalDerecho = casillas.filter { casilla ->
            casilla.casillaActual == CasillasTableroEnum.CASILLA_0_2 ||
            casilla.casillaActual == CasillasTableroEnum.CASILLA_1_1 ||
            casilla.casillaActual == CasillasTableroEnum.CASILLA_2_0
        }

        val jugador = diagonalDerecho.filter { casilla -> casilla.jugadorCasillaActual != JugadorCasillaEnum.NINGUNO }
        if (jugador.size < 3) return false
        val jugadorActual = jugador.first().jugadorCasillaActual
        val casillasJugadorGanador = jugador.filter { casilla -> casilla.jugadorCasillaActual == jugadorActual }
        if (casillasJugadorGanador.size < 3) return false
        this.hayGanador = true
        this.ganador = jugadorActual
        return true
    }
}
