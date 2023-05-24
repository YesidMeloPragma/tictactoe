package com.pragma.ticktactoe.mvvm

import androidx.lifecycle.MutableLiveData
import com.pragma.ticktactoe.constantes.EstadoJuegoEnum
import com.pragma.ticktactoe.models.DetalleCasillaTriqui

class JuegoViewModelImpl constructor(
    private val estadoActualTablero: MutableLiveData<List<DetalleCasillaTriqui>> = MutableLiveData<List<DetalleCasillaTriqui>>(),
    private var estadoActualJuego: MutableLiveData<EstadoJuegoEnum> = MutableLiveData<EstadoJuegoEnum>()
) : JuegoViewModel() {
    
    private val configuracionTablero = ConfigurarTableroHelper()

    init {
        reiniciarJuego()
    }

    override fun estadoActualTablero(): MutableLiveData<List<DetalleCasillaTriqui>> = estadoActualTablero

    override fun estadoActualJuego(): MutableLiveData<EstadoJuegoEnum> = estadoActualJuego

    override fun turno(
        estadoJuegoEnum: EstadoJuegoEnum,
        detalleCasillaTriqui: DetalleCasillaTriqui
    ) {

    }

    fun reiniciarJuego() {
        estadoActualTablero.postValue(configuracionTablero.generarEstadoInicalTablero().traerConfiguracionInicalTablero())
        estadoActualJuego.postValue(EstadoJuegoEnum.TURNO_JUGADOR1)
    }
}