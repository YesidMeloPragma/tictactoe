package com.pragma.ticktactoe.layouts

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.MutableLiveData
import com.pragma.ticktactoe.constantes.EstadoJuegoEnum
import com.pragma.ticktactoe.constantes.JugadorCasillaEnum
import com.pragma.ticktactoe.models.DetalleCasillaTriqui
import com.pragma.ticktactoe.mvvm.JuegoViewModel
import com.pragma.ticktactoe.ui.theme.Purple80

@SuppressWarnings("kotlin:S1186")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PHONE, showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.TABLET, widthDp = 891, heightDp = 411 , showBackground = true)
@Composable
fun prevScreenTictactoe() {
    val estadoTablero = MutableLiveData<MutableList<DetalleCasillaTriqui>>()
    val ganadorJuego = MutableLiveData<JugadorCasillaEnum>()
    val turnoActual = MutableLiveData<EstadoJuegoEnum>()
    ganadorJuego.value = JugadorCasillaEnum.EMPATE

    val viewModel = object : JuegoViewModel() {
        override fun estadoActualTablero()=estadoTablero

        override fun ganadorDelJuego() = ganadorJuego

        override fun notificarGanador() {}

        override fun reiniciarJuego() {}

        override fun turno(
            estadoJuegoEnum: EstadoJuegoEnum,
            detalleCasillaTriqui: DetalleCasillaTriqui
        ) {}

        override fun turnoActual() = turnoActual

    }
    TicTacToeScreen(juegoViewModel = viewModel)
}

@Composable
fun TicTacToeScreen(juegoViewModel: JuegoViewModel) {
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            TictactoeLandscape(juegoViewModel = juegoViewModel)
        }
        else -> {
            TictactoePortrait(juegoViewModel = juegoViewModel)
        }
    }
}

@Composable
fun TictactoePortrait(juegoViewModel: JuegoViewModel) {
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

            Pie(modifier = Modifier
                .weight(1f)
                .fillMaxWidth())
        }
    }
}


@Composable
fun TictactoeLandscape(juegoViewModel: JuegoViewModel) {
    val estadoActualTablero : MutableList<DetalleCasillaTriqui> by juegoViewModel.estadoActualTablero().observeAsState(initial = emptyList<DetalleCasillaTriqui>().toMutableList())
    val ganadorJuego: JugadorCasillaEnum by juegoViewModel.ganadorDelJuego().observeAsState(initial = JugadorCasillaEnum.NINGUNO)
    val turnoActual : EstadoJuegoEnum by juegoViewModel.turnoActual().observeAsState(initial = EstadoJuegoEnum.TURNO_JUGADOR1)

    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(Purple80)) {
        val (panelIzquierdo, panelDerecho) = createRefs()
        ConstraintLayout(modifier = Modifier
            .constrainAs(panelIzquierdo) {
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            end.linkTo(panelDerecho.start)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }) {
            val (encabezadoId, pieId) = createRefs()
            val guidelineBottom = createGuidelineFromBottom(0.3f)
            val guidelineTop = createGuidelineFromTop(0.2f)

            Encabezado(modifier = Modifier
                .constrainAs(encabezadoId){
                bottom.linkTo(guidelineBottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(guidelineTop)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }, turnoActual = turnoActual)

            Pie(modifier = Modifier
                .constrainAs(pieId){
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                top.linkTo(guidelineBottom)
                start.linkTo(parent.start)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            })
        }

        ConstraintLayout(modifier = Modifier
            .constrainAs(panelDerecho) {
            bottom.linkTo(parent.bottom)
            end.linkTo(parent.end)
            start.linkTo(panelIzquierdo.end)
            top.linkTo(parent.top)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }) {
            val (tableroId, ganadorId) = createRefs()
            if (ganadorJuego == JugadorCasillaEnum.NINGUNO) {
                Tablero(
                    modifier = Modifier.constrainAs(tableroId) {
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    },
                    estadoTablero = estadoActualTablero,
                    clicable = {
                        detalleCasillaTriqui ->
                        juegoViewModel.turno(estadoJuegoEnum = turnoActual, detalleCasillaTriqui = detalleCasillaTriqui)
                        juegoViewModel.notificarGanador()
                    }
                )
            } else {
                Ganador(modifier = Modifier.constrainAs(ganadorId){
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                },
                    jugadorCasillaEnum = ganadorJuego,
                    clicable = { juegoViewModel.reiniciarJuego() }
                )
            }
        }
    }
}