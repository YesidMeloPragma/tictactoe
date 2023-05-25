package com.pragma.ticktactoe.mvvm.JuegoViewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pragma.ticktactoe.constantes.CasillasTableroEnum
import com.pragma.ticktactoe.models.DetalleCasillaTriqui
import com.pragma.ticktactoe.mvvm.ConfigurarTableroHelper
import com.pragma.ticktactoe.mvvm.JuegoViewModel
import com.pragma.ticktactoe.mvvm.JuegoViewModelImpl
import com.pragma.ticktactoe.tools.MainCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseJuegoViewModelTest {
    @RelaxedMockK
    protected lateinit var configurarTableroHelper: ConfigurarTableroHelper
    protected lateinit var juegoViewModel: JuegoViewModel

    @get:Rule
    var  rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCorutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        juegoViewModel = JuegoViewModelImpl(configuracionTablero = configurarTableroHelper)
    }

    protected fun traerTablero() : MutableList<DetalleCasillaTriqui> {
        val listcasillasTablero = emptyList<DetalleCasillaTriqui>().toMutableList()
        CasillasTableroEnum.values().forEach {
            listcasillasTablero.add(DetalleCasillaTriqui().apply { this.casillaActual = it })
        }
        return listcasillasTablero
    }

}