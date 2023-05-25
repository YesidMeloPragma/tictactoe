package com.pragma.ticktactoe.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pragma.ticktactoe.constantes.EstadoJuegoEnum
import com.pragma.ticktactoe.models.DetalleCasillaTriqui
import kotlinx.coroutines.launch

class JuegoViewModelImpl constructor(
    private val configuracionTablero: ConfigurarTableroHelper = ConfigurarTableroHelperImpl(),
    private val finalizoJuegoHelper: FinalizoJuegoHelper = FinalizoJuegoHelperImpl()
) : JuegoViewModel() {

    private val estadoActualTablero: MutableLiveData<List<DetalleCasillaTriqui>> = MutableLiveData<List<DetalleCasillaTriqui>>()
    private val turnoActual: MutableLiveData<EstadoJuegoEnum> = MutableLiveData<EstadoJuegoEnum>()

    override fun estadoActualTablero(): MutableLiveData<List<DetalleCasillaTriqui>> = estadoActualTablero

    override fun turnoActual(): MutableLiveData<EstadoJuegoEnum> = turnoActual

    override fun turno(
        estadoJuegoEnum: EstadoJuegoEnum,
        detalleCasillaTriqui: DetalleCasillaTriqui
    ) {
        viewModelScope.launch {

        }
    }

    override fun reiniciarJuego() {
        viewModelScope.launch {
            val estadoInicialTablero = configuracionTablero.generarEstadoInicalTablero().traerConfiguracionInicalTablero()
            estadoActualTablero.postValue(estadoInicialTablero)
            turnoActual.postValue(EstadoJuegoEnum.TURNO_JUGADOR1)
        }
    }
}