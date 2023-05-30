package com.pragma.ticktactoe.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.pragma.ticktactoe.R
import com.pragma.ticktactoe.ui.theme.Purple80


@Preview(showBackground = true)
@Composable
fun prevPie() {
    Pie(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(color = Purple80))
}

@Composable
fun Pie(modifier: Modifier) {
    ConstraintLayout(modifier = modifier) {
        val imageId = createRef()
        val margin = 46.dp
        Image(
            painter = painterResource(id = R.mipmap.patrocinador),
            contentDescription = "",
            modifier = Modifier
                .constrainAs(imageId){
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end, margin = margin)
                    start.linkTo(parent.start, margin = margin)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
        )
    }
}