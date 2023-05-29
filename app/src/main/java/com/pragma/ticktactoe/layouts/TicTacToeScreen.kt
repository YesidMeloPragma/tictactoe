package com.pragma.ticktactoe.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import com.pragma.ticktactoe.constantes.EstadoJuegoEnum
import com.pragma.ticktactoe.constantes.JugadorCasillaEnum
import com.pragma.ticktactoe.models.DetalleCasillaTriqui
import com.pragma.ticktactoe.mvvm.JuegoViewModel
import com.pragma.ticktactoe.ui.theme.Purple80

@SuppressWarnings("kotlin:S1186")
@Preview(showBackground = true)
@Composable
fun prevScreenTictactoe() {
    val estadoTablero = MutableLiveData<MutableList<DetalleCasillaTriqui>>()
    val ganadorJuego = MutableLiveData<JugadorCasillaEnum>()
    val turnoActual = MutableLiveData<EstadoJuegoEnum>()
    ganadorJuego.value = JugadorCasillaEnum.JUGADOR1
    TicTacToeScreen(juegoViewModel = object : JuegoViewModel() {
        override fun estadoActualTablero()=estadoTablero

        override fun ganadorDelJuego() = ganadorJuego

        override fun notificarGanador() {}

        override fun reiniciarJuego() {}

        override fun turno(
            estadoJuegoEnum: EstadoJuegoEnum,
            detalleCasillaTriqui: DetalleCasillaTriqui
        ) {}

        override fun turnoActual() = turnoActual

    })
}

@Composable
fun TicTacToeScreen(juegoViewModel: JuegoViewModel) {
    val estadoActualTablero : MutableList<DetalleCasillaTriqui> by juegoViewModel.estadoActualTablero().observeAsState(initial = emptyList<DetalleCasillaTriqui>().toMutableList())
    val ganadorJuego: JugadorCasillaEnum by juegoViewModel.ganadorDelJuego().observeAsState(initial = JugadorCasillaEnum.NINGUNO)
    val turnoActual : EstadoJuegoEnum by juegoViewModel.turnoActual().observeAsState(initial = EstadoJuegoEnum.TURNO_JUGADOR1)

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Purple80)) {
        Column(modifier = Modifier.fillMaxSize()) {
            Encabezado(
                modifier = Modifier.weight(3f),
                turnoActual = turnoActual
            )
            if(ganadorJuego == JugadorCasillaEnum.NINGUNO) {
                Tablero(
                    estadoTablero = estadoActualTablero,
                    modifier = Modifier.weight(6f),
                    clicable = {
                            detalleCasillaTriqui ->
                        juegoViewModel.turno(estadoJuegoEnum = turnoActual, detalleCasillaTriqui = detalleCasillaTriqui)
                        juegoViewModel.notificarGanador()
                    }
                )
            } else {
                Ganador(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(6f),
                    jugadorCasillaEnum = ganadorJuego,
                    clicable = { juegoViewModel.reiniciarJuego() }
                )
            }

            Pie(modifier = Modifier.weight(1f))
        }
    }
}