package com.pragma.ticktactoe.mvvm.juegoViewModel

import io.mockk.coEvery
import io.mockk.verify
import org.junit.Test

class NotificarGanadorTest : BaseJuegoViewModelTest() {

    @Test
    fun noHayGanador() {
        //Given
        coEvery { finalizoJuegoHelper.hayGanador() } returns false

        //when
        juegoViewModel.notificarGanador()

        //then
        verify(exactly = 1) { finalizoJuegoHelper.hayGanador()  }
        verify(exactly = 0) { finalizoJuegoHelper.traerGanador()  }
    }

    @Test
    fun hayGanador() {
        //Given
        coEvery { finalizoJuegoHelper.hayGanador() } returns true

        //when
        juegoViewModel.notificarGanador()

        //then
        verify(exactly = 1) { finalizoJuegoHelper.hayGanador()  }
        verify(exactly = 1) { finalizoJuegoHelper.traerGanador()  }
    }


}