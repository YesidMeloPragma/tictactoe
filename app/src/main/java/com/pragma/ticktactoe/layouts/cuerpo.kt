package com.pragma.ticktactoe.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.pragma.ticktactoe.R
import com.pragma.ticktactoe.ui.theme.Purple80
import com.pragma.ticktactoe.ui.theme.PurpleTextColor
import com.pragma.ticktactoe.ui.theme.WhiteButtons

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun cuerpo() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Purple80)) {
        Column(modifier = Modifier.fillMaxSize()) {
            //region encabezado
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(3f)) {
                ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                    val (boton1, boton2) = createRefs()
                    val colorsButtons = ButtonDefaults.buttonColors(
                        containerColor = WhiteButtons,
                        contentColor = PurpleTextColor
                    )

                    Button(onClick = { /*TODO*/ },
                        modifier = Modifier
                        .constrainAs(boton1){
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(boton2.start)
                        bottom.linkTo(parent.bottom)
                    },
                        colors = colorsButtons
                    ) {
                        Text(text = "Jugador 1 O")
                    }

                    Button(onClick = { /*TODO*/ },
                        colors = colorsButtons,
                        modifier = Modifier.constrainAs(boton2){
                        start.linkTo(boton1.end)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }) {
                        Text(text = "Jugador 2 X")
                    }
                }
            }
            //endregion

            //region cuerpo
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(6f)) {
                ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                    val (imagenTablero) = createRefs()
                    Image(
                        painter = painterResource(id = R.drawable.triqui),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .constrainAs(imagenTablero) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom)
                            }
                    )
                }
            }
            //endregion

            Image(
                painter = painterResource(id = R.mipmap.patrocinador),
                contentDescription = "",
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .graphicsLayer {
                        scaleX = maxOf(.5f, 3f)
                        scaleY = maxOf(.5f, 3f)
                    }
            )

        }
    }
}


