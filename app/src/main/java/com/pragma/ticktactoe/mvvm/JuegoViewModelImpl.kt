package com.pragma.ticktactoe.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pragma.ticktactoe.constantes.EstadoJuegoEnum
import com.pragma.ticktactoe.models.DetalleCasillaTriqui
import com.pragma.ticktactoe.mvvm.helpers.configurarTableroHelper.ConfigurarTableroHelper
import com.pragma.ticktactoe.mvvm.helpers.configurarTableroHelper.ConfigurarTableroHelperImpl
import com.pragma.ticktactoe.mvvm.helpers.finalizoJuegoHelper.FinalizoJuegoHelper
import com.pragma.ticktactoe.mvvm.helpers.finalizoJuegoHelper.FinalizoJuegoHelperImpl
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

            if (!finalizoJuegoHelper.hayGanador()) return@launch
            finalizoJuegoHelper.validarGanador(casillas = estadoActualTablero.value!!)
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