package com.pragma.ticktactoe.layouts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintLayout
import com.pragma.ticktactoe.constantes.EstadoJuegoEnum
import com.pragma.ticktactoe.constantes.JugadorCasillaEnum
import com.pragma.ticktactoe.ui.theme.Purple80
import com.pragma.ticktactoe.ui.theme.PurpleTextColor
import com.pragma.ticktactoe.ui.theme.WhiteButtons

@Composable
fun Encabezado(
    modifier: Modifier,
    turnoActual : EstadoJuegoEnum,
    ganadorJuego: JugadorCasillaEnum,
    reiniciarJuego: ()->Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (boton1, boton2, ganadorJuegoId) = createRefs()

            Button(onClick = {},
                modifier = Modifier
                    .constrainAs(boton1){
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(boton2.start)
                        bottom.linkTo(parent.bottom)
                    },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if(turnoActual == EstadoJuegoEnum.TURNO_JUGADOR1) PurpleTextColor else WhiteButtons,
                    contentColor = if(turnoActual == EstadoJuegoEnum.TURNO_JUGADOR1) WhiteButtons  else PurpleTextColor,
                )
            ) {
                Text(text = "Jugador 1 O")
            }

            Button(onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = if(turnoActual == EstadoJuegoEnum.TURNO_JUGADOR2) PurpleTextColor else WhiteButtons,
                    contentColor = if(turnoActual == EstadoJuegoEnum.TURNO_JUGADOR2) WhiteButtons  else PurpleTextColor,
                ),
                modifier = Modifier.constrainAs(boton2){
                    start.linkTo(boton1.end)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }) {
                Text(text = "Jugador 2 X")
            }

            Text(
                color = PurpleTextColor,
                text = if (ganadorJuego != JugadorCasillaEnum.NINGUNO) {
                        "Gano el jugador $ganadorJuego Volver a jugar"
                    } else { "" },
                modifier = Modifier.constrainAs(ganadorJuegoId){
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }.clickable { reiniciarJuego.invoke() }
            )

        }
    }
}