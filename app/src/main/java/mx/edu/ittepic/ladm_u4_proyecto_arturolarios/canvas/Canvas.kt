package mx.edu.ittepic.ladm_u4_proyecto_arturolarios.canvas

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import mx.edu.ittepic.ladm_u4_proyecto_arturolarios.GameActivity
import mx.edu.ittepic.ladm_u4_proyecto_arturolarios.common.Game

@SuppressLint("ViewConstructor")
class Canvas(main : GameActivity, type : String) : View(main)
{
    private var game : Game = Game(this, type)

    init {
        game.load()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val p = Paint()
        game.draw(p, canvas)
        invalidate()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean
    {
        game.event(event)
        return true
    }
}