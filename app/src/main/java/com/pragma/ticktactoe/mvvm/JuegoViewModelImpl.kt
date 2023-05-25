package com.pragma.ticktactoe.mvvm

import androidx.lifecycle.LiveData
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

    private val empate: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private val estadoActualTablero: MutableLiveData<MutableList<DetalleCasillaTriqui>> = MutableLiveData<MutableList<DetalleCasillaTriqui>>()
    private val turnoActual: MutableLiveData<EstadoJuegoEnum> = MutableLiveData<EstadoJuegoEnum>()
    private val ganadorJuego: MutableLiveData<JugadorCasillaEnum> = MutableLiveData()

    override fun estadoActualTablero(): LiveData<MutableList<DetalleCasillaTriqui>> = estadoActualTablero

    override fun ganadorDelJuego(): LiveData<JugadorCasillaEnum> = ganadorJuego

    override fun reiniciarJuego() {
        viewModelScope.launch {
            finalizoJuegoHelper.reiniciar()
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
            if (finalizoJuegoHelper.hayGanador()) return@launch

            val tablero = actualizarTableroHelper.actualizarTablero(casillasTablero = estadoActualTablero.value!!, detalleCasillaTriqui = detalleCasillaTriqui)
            estadoActualTablero.postValue(tablero)

            detalleCasillaTriqui.apply {
                jugadorCasillaActual = when(turnoActual.value!!) {
                    EstadoJuegoEnum.TURNO_JUGADOR1 -> JugadorCasillaEnum.JUGADOR1
                    EstadoJuegoEnum.TURNO_JUGADOR2 -> JugadorCasillaEnum.JUGADOR2
                    EstadoJuegoEnum.REINICIAR_JUEGO -> JugadorCasillaEnum.NINGUNO
                }
            }

            turnoActual.postValue(when(estadoJuegoEnum) {
                EstadoJuegoEnum.TURNO_JUGADOR1 -> EstadoJuegoEnum.TURNO_JUGADOR2
                EstadoJuegoEnum.TURNO_JUGADOR2 -> EstadoJuegoEnum.TURNO_JUGADOR1
                EstadoJuegoEnum.REINICIAR_JUEGO -> EstadoJuegoEnum.TURNO_JUGADOR1
            })
            finalizoJuegoHelper.validarGanador(casillas = tablero)

            if (!finalizoJuegoHelper.hayGanador()) return@launch
            ganadorJuego.postValue(finalizoJuegoHelper.traerGanador())
        }
    }

    override fun turnoActual(): LiveData<EstadoJuegoEnum> = turnoActual

}