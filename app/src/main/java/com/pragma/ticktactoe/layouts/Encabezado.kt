package com.pragma.ticktactoe.layouts

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.pragma.ticktactoe.constantes.EstadoJuegoEnum
import com.pragma.ticktactoe.ui.theme.MyTypography
import com.pragma.ticktactoe.ui.theme.PurpleTextColor
import com.pragma.ticktactoe.ui.theme.WhiteButtons
import com.pragma.ticktactoe.ui.theme.WhiteButtons1

@Preview(uiMode = UI_MODE_NIGHT_NO, device = Devices.PHONE, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_NO, device = Devices.TABLET, widthDp = 891, heightDp = 411 , showBackground = true)
@Composable
fun prevEncabezado() {
    Encabezado(modifier = Modifier.fillMaxSize(), turnoActual = EstadoJuegoEnum.TURNO_JUGADOR2)
}


@Composable
fun Encabezado(
    modifier: Modifier,
    turnoActual : EstadoJuegoEnum
) {
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            EncabezadoLandscape(modifier = modifier, turnoActual = turnoActual)
        }
        else -> {
            EncabezadoPortrait(modifier = modifier, turnoActual = turnoActual)
        }
    }
}

@Composable
fun EncabezadoLandscape(modifier: Modifier,
             turnoActual : EstadoJuegoEnum){
    Box(modifier = modifier.fillMaxSize()) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (boton1, boton2) = createRefs()
            customButtonTurn(
                modifier = Modifier.constrainAs(boton1) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(boton2.top)
                },
                text = "Jugador 1 O",
                turnoActual = turnoActual,
                jugadorBoton = EstadoJuegoEnum.TURNO_JUGADOR1)

            customButtonTurn(
                modifier = Modifier.constrainAs(boton2){
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    top.linkTo(boton1.top)
                },
                text = "Jugador 2 X",
                turnoActual = turnoActual,
                jugadorBoton = EstadoJuegoEnum.TURNO_JUGADOR2)
        }
    }
}

@Composable
fun EncabezadoPortrait(modifier: Modifier,
             turnoActual : EstadoJuegoEnum){
    Box(modifier = modifier.fillMaxSize()) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (boton1, boton2) = createRefs()
            customButtonTurn(
                modifier = Modifier.constrainAs(boton1) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(boton2.start)
                    bottom.linkTo(parent.bottom)
                },
                text = "Jugador 1 O",
                turnoActual = turnoActual,
                jugadorBoton = EstadoJuegoEnum.TURNO_JUGADOR1)

            customButtonTurn(
                modifier = Modifier.constrainAs(boton2){
                    start.linkTo(boton1.end)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
                text = "Jugador 2 X",
                turnoActual = turnoActual,
                jugadorBoton = EstadoJuegoEnum.TURNO_JUGADOR2)
        }
    }
}


@Composable
fun customButtonTurn(modifier: Modifier, text:String, turnoActual: EstadoJuegoEnum, jugadorBoton: EstadoJuegoEnum){
    ConstraintLayout(modifier = modifier
        .wrapContentHeight()
        .wrapContentWidth()) {
        val (opacityId, textID) = createRefs()
        Button(
            onClick = {},
            elevation = ButtonDefaults.elevatedButtonElevation(4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = WhiteButtons,
                contentColor = PurpleTextColor,
            ),
        ) {
            Text(text = text, style = MyTypography.bodyMedium)
        }
        if (turnoActual == jugadorBoton) {
            Box(modifier = Modifier
                .background(WhiteButtons1, shape = RoundedCornerShape(30.dp))
                .constrainAs(opacityId) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                })
        }
    }

}