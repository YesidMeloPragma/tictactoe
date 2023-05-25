package com.pragma.ticktactoe.constantes

enum class CasillasTableroEnum(private val fila: Int, private val columna: Int) {
    CASILLA_0_0(fila = 0, columna = 0),
    CASILLA_0_1(fila = 0, columna = 1),
    CASILLA_0_2(fila = 0, columna = 2),
    CASILLA_1_0(fila = 1, columna = 0),
    CASILLA_1_1(fila = 1, columna = 1),
    CASILLA_1_2(fila = 1, columna = 2),
    CASILLA_2_0(fila = 2, columna = 0),
    CASILLA_2_1(fila = 2, columna = 1),
    CASILLA_2_2(fila = 2, columna = 2),
    ;

    fun traerFila() = fila
    fun traerColumna() = columna
}
