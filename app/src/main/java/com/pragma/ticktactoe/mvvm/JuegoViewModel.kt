package com.pragma.ticktactoe.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.pragma.ticktactoe.constantes.EstadoJuegoEnum
import com.pragma.ticktactoe.constantes.JugadorCasillaEnum
import com.pragma.ticktactoe.models.DetalleCasillaTriqui

abstract class JuegoViewModel : ViewModel() {
    abstract fun estadoActualTablero() : LiveData<MutableList<DetalleCasillaTriqui>>
    abstract fun ganadorDelJuego() : LiveData<JugadorCasillaEnum>
    abstract fun reiniciarJuego()
    abstract fun turno(estadoJuegoEnum: EstadoJuegoEnum, detalleCasillaTriqui: DetalleCasillaTriqui)
    abstract fun turnoActual(): LiveData<EstadoJuegoEnum>
}
