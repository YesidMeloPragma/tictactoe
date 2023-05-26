package com.pragma.ticktactoe.mvvm.configurarTableroHelper

import com.pragma.ticktactoe.mvvm.helpers.configurarTableroHelper.ConfigurarTableroHelper
import com.pragma.ticktactoe.mvvm.helpers.configurarTableroHelper.ConfigurarTableroHelperImpl
import org.junit.Before

abstract class BaseConfigurarTableroHelperTest {

    protected lateinit var configurarTableroHelper : ConfigurarTableroHelper

    @Before
    fun setUp() {
        configurarTableroHelper = ConfigurarTableroHelperImpl()
    }
}