package com.pragma.ticktactoe.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pragma.ticktactoe.constantes.EstadoJuegoEnum
import com.pragma.ticktactoe.models.DetalleCasillaTriqui

abstract class JuegoViewModel : ViewModel() {
    abstract fun estadoActualTablero() : MutableLiveData<List<DetalleCasillaTriqui>>
    abstract fun turnoActual(): MutableLiveData<EstadoJuegoEnum>
    abstract fun reiniciarJuego()
    abstract fun turno(estadoJuegoEnum: EstadoJuegoEnum, detalleCasillaTriqui: DetalleCasillaTriqui)
}
