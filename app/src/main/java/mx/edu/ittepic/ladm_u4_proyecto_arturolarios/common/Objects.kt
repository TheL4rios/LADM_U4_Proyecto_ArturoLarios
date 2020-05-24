package mx.edu.ittepic.ladm_u4_proyecto_arturolarios.common

import android.graphics.Bitmap
import android.graphics.Canvas

data class Player(val name : String, var score : Int)
data class Block(val block : Bitmap, val width : Int, val height : Int, var alive : Boolean, val x: Float, val y: Float)
data class Stick(var stick : Bitmap, val width: Int, val height: Int, var x : Float, val y : Float)
data class Ball(val ball : Bitmap, val width: Int, val height: Int, val canvas: Canvas)
{
    var x = canvas.width / 2f
    var y = canvas.height - canvas.height * 0.201f - height.toFloat()
    var incX = 10 * arrayOf(1, -1)[(0..1).random()]
    var incY = -10

    fun move()
    {
        x += incX
        if(x <= 0 || x >= canvas.width - ball.width)
        {
            incX *= -1
        }

        if(y <= 0 || y >= canvas.height)
        {
            incY *= -1
        }
        y += incY
    }
}