package com.pragma.ticktactoe.mvvm.finalizoJuego

import com.pragma.ticktactoe.mvvm.helpers.finalizoJuegoHelper.FinalizoJuegoHelper
import com.pragma.ticktactoe.mvvm.helpers.finalizoJuegoHelper.FinalizoJuegoHelperImpl
import org.junit.Before

abstract class BaseFinalizoJuegoHelperTest {

    lateinit var finalizoJuegoHelper: FinalizoJuegoHelper

    @Before
    fun setUp() {
        finalizoJuegoHelper = FinalizoJuegoHelperImpl()
    }
}