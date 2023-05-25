package com.pragma.ticktactoe.mvvm.actualizarTableroHelper

import com.pragma.ticktactoe.constantes.CasillasTableroEnum
import com.pragma.ticktactoe.models.DetalleCasillaTriqui
import com.pragma.ticktactoe.mvvm.helpers.actualizarTableroHelper.ActualizarTableroHelper
import com.pragma.ticktactoe.mvvm.helpers.actualizarTableroHelper.ActualizarTableroHelperImpl
import org.junit.Before

abstract class BaseActualizarTableroHelperTest {

    lateinit var actualizarTableroHelper: ActualizarTableroHelper

    @Before
    fun setUp() {
        actualizarTableroHelper = ActualizarTableroHelperImpl()
    }

    protected fun traerTablero() : MutableList<DetalleCasillaTriqui> {
        val listcasillasTablero = emptyList<DetalleCasillaTriqui>().toMutableList()
        CasillasTableroEnum.values().forEach {
            listcasillasTablero.add(DetalleCasillaTriqui().apply { this.casillaActual = it })
        }
        return listcasillasTablero
    }
}