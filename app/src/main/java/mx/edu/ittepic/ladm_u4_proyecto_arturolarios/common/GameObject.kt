package mx.edu.ittepic.ladm_u4_proyecto_arturolarios.common

import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent

class GameObject(val type : Any) : Renderer
{
    override fun draw(p: Paint, canvas: Canvas, x : Float, y : Float)
    {
        if (type is Block)
            canvas.drawBitmap(type.block, x, y, p)
        if (type is Stick)
            canvas.drawBitmap(type.stick, x, type.y, p)
        if (type is Ball)
            canvas.drawBitmap(type.ball, type.x, type.y, p)
    }

    override fun isVisible(): Boolean {
        if (type is Block)
            return type.alive

        return super.isVisible()
    }

    fun move()
    {
        if (type is Ball)
            type.move()
    }

    fun hide()
    {
        if (type is Block)
        {
            type.alive = false
        }
    }

    fun getX() : Float
    {
        if (type is Stick)
            return type.x
        if (type is Block)
            return type.x
        if (type is Ball)
            return type.x
        return 0f
    }

    fun getY() : Float
    {
        if (type is Stick)
            return type.y
        if (type is Block)
            return type.y
        if (type is Ball)
            return type.y
        return 0f
    }


    fun setX(x : Float)
    {
        if (type is Stick)
            type.x = x
    }

    fun setY(y : Float)
    {
        if (type is Ball)
            type.y = y
    }

    fun invY()
    {
        if (type is Ball)
            type.incY *= -1
    }

    fun invX()
    {
        if (type is Ball)
            type.incX *= -1
    }

    fun getWidth() : Int
    {
        if (type is Block)
            return type.width
        if (type is Stick)
            return type.width
        if (type is Ball)
            return type.width

        return 0
    }

    fun getHeight() : Int
    {
        if (type is Block)
            return type.height
        if (type is Stick)
            return type.height
        if (type is Ball)
            return type.height

        return 0
    }

    fun collisionSide(gameObject: GameObject) : Boolean
    {
        if (type is Ball)
        {
            if (gameObject.type is Stick)
            {
                val overlapLeft = type.x + type.width - gameObject.type.x
                val overlapRight = type.x - gameObject.type.x + gameObject.type.width
                val overlapTop = type.y + type.height - gameObject.type.y

                val minX = if (overlapLeft < overlapRight) overlapLeft else overlapRight

                if (minX < overlapTop)
                    return true
            }
            else if (gameObject.type is Block)
            {
                val overlapLeft = type.x + type.width - gameObject.type.x
                val overlapRight = type.x - gameObject.type.x + gameObject.type.width
                val overlapTop = type.y + type.height - gameObject.type.y
                val overlapBottom = type.y - gameObject.type.y + gameObject.type.height

                val minX = if (overlapLeft < overlapRight) overlapLeft else overlapRight
                val minY = if (overlapTop < overlapBottom) overlapTop else overlapBottom

                if (minX < minY)
                    return true
            }
        }
        return false
    }

    fun collision(gameObject: GameObject) : Boolean
    {
        if (type is Ball)
        {
            if (gameObject.type is Stick)
            {
                if (type.y + type.height >= gameObject.type.y && type.y <= gameObject.type.y + gameObject.type.height)
                {
                    if (type.x >= gameObject.type.x && type.x <= gameObject.type.x + gameObject.type.width)
                    {
                        return true
                    }
                }
            }
            else if (gameObject.type is Block)
            {
                if (type.y + type.height >= gameObject.type.y && type.y <= gameObject.type.y + gameObject.type.height)
                {
                    if (type.x >= gameObject.type.x && type.x <= gameObject.type.x + gameObject.type.width)
                    {
                        return true
                    }
                }
            }
        }

        return false
    }

    fun touchIt(event: MotionEvent) : Boolean
    {
        if (type is Stick)
        {
            if (event.x >= type.x && event.x <= type.x + type.width)
                return true
        }

        return false
    }
}