package mx.edu.ittepic.ladm_u4_proyecto_arturolarios.common

import android.graphics.Canvas
import android.graphics.Paint

interface Renderer
{
    fun draw(p : Paint, canvas: Canvas, x : Float, y : Float)
    fun isVisible() : Boolean = true
}