package com.example.appstore.screens

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.appstore.ui.theme.Purple40
import com.example.appstore.ui.theme.PurpleGrey40

@Composable
fun Loader() {
    val strokeWidth = 5.dp
    CircularProgressIndicator(
        modifier = Modifier.drawBehind {
            drawCircle(
                Purple40,
                radius = size.width / 2 - strokeWidth.toPx() / 2,
                style = Stroke(strokeWidth.toPx())
            )
        },
        color = PurpleGrey40,
        strokeWidth = strokeWidth
    )
}