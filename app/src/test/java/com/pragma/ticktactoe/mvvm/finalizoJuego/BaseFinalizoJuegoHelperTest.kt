package com.pragma.ticktactoe.mvvm.finalizoJuego

import com.pragma.ticktactoe.mvvm.FinalizoJuegoHelper
import com.pragma.ticktactoe.mvvm.FinalizoJuegoHelperImpl
import org.junit.After
import org.junit.Before

abstract class BaseFinalizoJuegoHelperTest {

    lateinit var finalizoJuegoHelper: FinalizoJuegoHelper

    @Before
    fun setUp() {
        finalizoJuegoHelper = FinalizoJuegoHelperImpl()
    }
}