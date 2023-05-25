package com.pragma.ticktactoe.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.pragma.ticktactoe.R
import com.pragma.ticktactoe.constantes.CasillasTableroEnum
import com.pragma.ticktactoe.constantes.JugadorCasillaEnum
import com.pragma.ticktactoe.models.DetalleCasillaTriqui
import com.pragma.ticktactoe.ui.theme.Purple80

@Composable
fun Tablero(
    modifier: Modifier,
    estadoTablero: MutableList<DetalleCasillaTriqui>,
    clicable: (DetalleCasillaTriqui)-> Unit
) {

    Box(modifier = modifier.fillMaxWidth()
        ) {
        tablero(
            estadoTablero = estadoTablero,
            clicable = clicable
        )
    }
}

@Composable
fun tablero(
    estadoTablero: MutableList<DetalleCasillaTriqui>,
    clicable: (DetalleCasillaTriqui)-> Unit
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (
            fondoId,
            casilla_0_0,
            casilla_0_1,
            casilla_0_2,
            casilla_1_0,
            casilla_1_1,
            casilla_1_2,
            casilla_2_0,
            casilla_2_1,
            casilla_2_2,
            contenedorId
        ) = createRefs()
        val separacion = 20.dp

        Box(modifier = Modifier
            .size(300.dp)
            .background(Purple80)
            .constrainAs(contenedorId) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                fondo(modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(fondoId) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })

                casilla(
                    identificador = CasillasTableroEnum.CASILLA_1_1,
                    estadoTablero = estadoTablero,
                    clicable = clicable,
                    modifier = Modifier.constrainAs(casilla_1_1){
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    })

                casilla(
                    identificador = CasillasTableroEnum.CASILLA_0_0,
                    estadoTablero = estadoTablero,
                    clicable = clicable,
                    modifier = Modifier.constrainAs(casilla_0_0){
                        bottom.linkTo(casilla_1_1.top, margin = separacion)
                        end.linkTo(casilla_1_1.start, margin = separacion)
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    })

                casilla(
                    identificador = CasillasTableroEnum.CASILLA_0_1,
                    estadoTablero = estadoTablero,
                    clicable = clicable,
                    modifier = Modifier.constrainAs(casilla_0_1){
                        bottom.linkTo(casilla_1_1.top, margin = separacion)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    })

                casilla(
                    identificador = CasillasTableroEnum.CASILLA_0_2,
                    estadoTablero = estadoTablero,
                    clicable = clicable,
                    modifier = Modifier.constrainAs(casilla_0_2){
                        bottom.linkTo(casilla_1_1.top, margin = separacion)
                        end.linkTo(parent.end)
                        start.linkTo(casilla_1_1.end, margin = separacion)
                        top.linkTo(parent.top)
                    })

                casilla(
                    identificador = CasillasTableroEnum.CASILLA_1_0,
                    estadoTablero = estadoTablero,
                    clicable = clicable,
                    modifier = Modifier.constrainAs(casilla_1_0){
                        bottom.linkTo(parent.bottom)
                        end.linkTo(casilla_1_1.start, margin = separacion)
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    })

                casilla(
                    identificador = CasillasTableroEnum.CASILLA_1_2,
                    estadoTablero = estadoTablero,
                    clicable = clicable,
                    modifier = Modifier.constrainAs(casilla_1_2){
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(casilla_1_1.end, margin = separacion)
                        top.linkTo(parent.top)
                    })

                casilla(
                    identificador = CasillasTableroEnum.CASILLA_2_0,
                    estadoTablero = estadoTablero,
                    clicable = clicable,
                    modifier = Modifier.constrainAs(casilla_2_0){
                        bottom.linkTo(parent.bottom)
                        end.linkTo(casilla_1_1.start, margin = separacion)
                        start.linkTo(parent.start)
                        top.linkTo(casilla_1_1.bottom, margin = separacion)
                    })

                casilla(
                    identificador = CasillasTableroEnum.CASILLA_2_1,
                    estadoTablero = estadoTablero,
                    clicable = clicable,
                    modifier = Modifier.constrainAs(casilla_2_1){
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        top.linkTo(casilla_1_1.bottom, margin = separacion)
                    })

                casilla(
                    identificador = CasillasTableroEnum.CASILLA_2_2,
                    estadoTablero = estadoTablero,
                    clicable = clicable,
                    modifier = Modifier.constrainAs(casilla_2_2){
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(casilla_1_1.end, margin = separacion)
                        top.linkTo(casilla_1_1.bottom, margin = separacion)
                    })
            }
        }
    }
}

@Composable
fun casilla(
    modifier: Modifier,
    identificador: CasillasTableroEnum,
    estadoTablero: MutableList<DetalleCasillaTriqui>,
    clicable: (DetalleCasillaTriqui)-> Unit
) {
    val detalleCasilla = estadoTablero.find { det -> det.casillaActual == identificador } ?: return
    if (detalleCasilla.jugadorCasillaActual == JugadorCasillaEnum.NINGUNO) {
        Box(modifier = modifier
            .size(90.dp)
            .clickable {
                clicable.invoke(DetalleCasillaTriqui().apply { casillaActual = identificador })
            }
        )
        return
    }
    val id = when(detalleCasilla.jugadorCasillaActual) {
        JugadorCasillaEnum.NINGUNO -> R.mipmap.o
        JugadorCasillaEnum.JUGADOR1 -> R.mipmap.o
        JugadorCasillaEnum.JUGADOR2 -> R.mipmap.x
    }
    Image(
        painter = painterResource(id = id),
        contentDescription = "",
        modifier = modifier,
        alpha = 1f
    )
}

@Composable
fun fondo(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.triqui),
        contentDescription = "",
        modifier = modifier.fillMaxWidth()
    )
}
