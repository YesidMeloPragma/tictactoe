package com.pragma.ticktactoe.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import com.pragma.ticktactoe.R

@Composable
fun Pie(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.mipmap.patrocinador),
        contentDescription = "",
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer {
                scaleX = maxOf(.5f, 3f)
                scaleY = maxOf(.5f, 3f)
            }
    )
}