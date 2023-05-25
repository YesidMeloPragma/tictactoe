package com.pragma.ticktactoe.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pragma.ticktactoe.constantes.EstadoJuegoEnum
import com.pragma.ticktactoe.constantes.JugadorCasillaEnum
import com.pragma.ticktactoe.models.DetalleCasillaTriqui
import com.pragma.ticktactoe.mvvm.helpers.actualizarTableroHelper.ActualizarTableroHelper
import com.pragma.ticktactoe.mvvm.helpers.actualizarTableroHelper.ActualizarTableroHelperImpl
import com.pragma.ticktactoe.mvvm.helpers.configurarTableroHelper.ConfigurarTableroHelper
import com.pragma.ticktactoe.mvvm.helpers.configurarTableroHelper.ConfigurarTableroHelperImpl
import com.pragma.ticktactoe.mvvm.helpers.finalizoJuegoHelper.FinalizoJuegoHelper
import com.pragma.ticktactoe.mvvm.helpers.finalizoJuegoHelper.FinalizoJuegoHelperImpl
import kotlinx.coroutines.launch

class JuegoViewModelImpl constructor(
    private val actualizarTableroHelper: ActualizarTableroHelper = ActualizarTableroHelperImpl(),
    private val configuracionTablero: ConfigurarTableroHelper = ConfigurarTableroHelperImpl(),
    private val finalizoJuegoHelper: FinalizoJuegoHelper = FinalizoJuegoHelperImpl()
) : JuegoViewModel() {

    private val estadoActualTablero: MutableLiveData<MutableList<DetalleCasillaTriqui>> = MutableLiveData<MutableList<DetalleCasillaTriqui>>()
    private val turnoActual: MutableLiveData<EstadoJuegoEnum> = MutableLiveData<EstadoJuegoEnum>()
    private val ganadorJuego: MutableLiveData<JugadorCasillaEnum> = MutableLiveData()

    override fun estadoActualTablero(): MutableLiveData<MutableList<DetalleCasillaTriqui>> = estadoActualTablero

    override fun ganadorDelJuego(): MutableLiveData<JugadorCasillaEnum> = ganadorJuego

    override fun reiniciarJuego() {
        viewModelScope.launch {
            val estadoInicialTablero = configuracionTablero.generarEstadoInicalTablero().traerConfiguracionInicalTablero()
            estadoActualTablero.postValue(estadoInicialTablero)
            turnoActual.postValue(EstadoJuegoEnum.TURNO_JUGADOR1)
            ganadorJuego.postValue(JugadorCasillaEnum.NINGUNO)
        }
    }

    override fun turno(
        estadoJuegoEnum: EstadoJuegoEnum,
        detalleCasillaTriqui: DetalleCasillaTriqui
    ) {
        viewModelScope.launch {
            estadoActualTablero.postValue(actualizarTableroHelper.actualizarTablero(casillasTablero = estadoActualTablero.value!!, detalleCasillaTriqui = detalleCasillaTriqui))
            finalizoJuegoHelper.validarGanador(casillas = estadoActualTablero.value!!)

            turnoActual.postValue(when(estadoJuegoEnum) {
                EstadoJuegoEnum.TURNO_JUGADOR1 -> EstadoJuegoEnum.TURNO_JUGADOR2
                EstadoJuegoEnum.TURNO_JUGADOR2 -> EstadoJuegoEnum.TURNO_JUGADOR1
                EstadoJuegoEnum.REINICIAR_JUEGO -> EstadoJuegoEnum.TURNO_JUGADOR1
            })

            if (!finalizoJuegoHelper.hayGanador()) return@launch
            ganadorJuego.postValue(finalizoJuegoHelper.traerGanador())
        }
    }

    override fun turnoActual(): MutableLiveData<EstadoJuegoEnum> = turnoActual

}