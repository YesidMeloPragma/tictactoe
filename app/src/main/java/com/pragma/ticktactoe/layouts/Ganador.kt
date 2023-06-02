package com.pragma.ticktactoe.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.pragma.ticktactoe.R
import com.pragma.ticktactoe.constantes.JugadorCasillaEnum
import com.pragma.ticktactoe.ui.theme.Black
import com.pragma.ticktactoe.ui.theme.MyTypography
import com.pragma.ticktactoe.ui.theme.PurpleTextWinner
import com.pragma.ticktactoe.ui.theme.PurpleWinner
import com.pragma.ticktactoe.utils.advancedShadow

@Preview(showBackground = true)
@Composable
fun PrevGanador() {
    Ganador(modifier = Modifier.fillMaxSize(), jugadorCasillaEnum = JugadorCasillaEnum.JUGADOR1){}
}

@Composable
fun Ganador(
    modifier: Modifier,
    jugadorCasillaEnum: JugadorCasillaEnum,
    clicable: ()->Unit
) {
    ConstraintLayout(modifier = modifier) {
        val (cardPrincipal, imagen, textoId) = createRefs()
        ConstraintLayout(modifier = Modifier
            .width(344.dp)
            .height(160.dp)
            .advancedShadow(
                color = Black,
                offsetY = 3.dp,
                offsetX = 0.dp,
                alpha = 0.31f,
                shadowBlurRadius = 5.dp,
                cornersRadius = 16.dp
            )
            .background(PurpleWinner, shape = RoundedCornerShape(16.dp))
            .clickable { clicable.invoke() }
            .constrainAs(cardPrincipal) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            }) {
            Image(
                painter = painterResource(id = R.drawable.persona),
                contentDescription ="",
                modifier = Modifier.constrainAs(imagen){
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start, margin = 13.dp)
                    top.linkTo(parent.top)
                }
            )
            Text(
                textAlign = TextAlign.Start,
                text = "Ganador $jugadorCasillaEnum",
                style = MyTypography.bodyLarge.copy(color = PurpleTextWinner),
                modifier = Modifier
                    .wrapContentHeight()
                    .constrainAs(textoId) {
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    start.linkTo(imagen.end, margin = 52.dp)
                    top.linkTo(parent.top, margin = 16.dp)
                    end.linkTo(parent.end, margin = 20.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
            )
        }
    }
}