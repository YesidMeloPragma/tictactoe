package com.pragma.ticktactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.pragma.ticktactoe.layouts.TicTacToeScreen
import com.pragma.ticktactoe.mvvm.JuegoViewModel
import com.pragma.ticktactoe.mvvm.JuegoViewModelImpl
import com.pragma.ticktactoe.ui.theme.TicktactoeTheme

class MainActivity : ComponentActivity() {

    private val viewModel :  JuegoViewModel = JuegoViewModelImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicktactoeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TicTacToeScreen(juegoViewModel = viewModel)
                }
            }
        }
        viewModel.reiniciarJuego()
    }
}
