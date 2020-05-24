package mx.edu.ittepic.ladm_u4_proyecto_arturolarios.common

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.MotionEvent
import mx.edu.ittepic.ladm_u4_proyecto_arturolarios.SERVER
import mx.edu.ittepic.ladm_u4_proyecto_arturolarios.bluetooth.*
import mx.edu.ittepic.ladm_u4_proyecto_arturolarios.canvas.Canvas
import mx.edu.ittepic.ladm_u4_proyecto_arturolarios.canvas.Sprites

class Game(private val canvas : Canvas, private val type : String)
{
    lateinit var player1 : Player
    lateinit var player2 : Player
    lateinit var ball : GameObject

    private val sprites = Sprites(canvas, type)
    private var blocks = ArrayList<ArrayList<GameObject>>()
    private var sticks = ArrayList<GameObject>()

    var start = false
    var dead = false

    private var win = false
    private var bluetoothService : BluetoothService? = null
    private var p : Paint? = null
    private var c : android.graphics.Canvas? = null
    private var winner = false
    private var stick : GameObject? = null
    private var firstTime = true

    fun load()
    {
        loadAssets()
        player1 = Player("Tú", 0)
        player2 = Player("Riv", 0)
        startBluetoothService()
    }

    fun draw(p : Paint, canvas: android.graphics.Canvas?)
    {
        canvas?.let { c ->
            this.p = p
            this.c = canvas
            val screen = Rect(0, 0, c.width, c.height)
            c.drawBitmap(sprites.background, null, screen, null)

            if (firstTime)
            {
                getBlocks(c)
                getBall(c)
                getStick(c)
                firstTime = false
            }

            drawScore(p, c)

            drawBlocks(p, c)

            ball.draw(p, c, 0f, 0f)

            if (start && !dead && !win)
            {
                ball.move()
            }

            collisionDetect(c)

            drawStick(p, c)
        }
    }

    private fun drawScore(p : Paint, c : android.graphics.Canvas)
    {
        p.textSize = 100f
        p.color = Color.WHITE
        c.drawText("${player1.name}: ${player1.score}", 100f, 100f, p)
        c.drawText("${player2.name}: ${player2.score}", 650f, 100f, p)

        if (player1.score == 48)
        {
            if (!winner)
                bluetoothService?.write(DEAD.toByteArray())
            dead = true
            checkWin()
        }
    }

    private fun collisionDetect(c : android.graphics.Canvas) {
        if (ball.collision(sticks[0]))
        {
            if (ball.collisionSide(sticks[0]))
                ball.invX()
            else
                ball.invY()
        }

        if (ball.getY() >= c.height && !dead)
        {
            dead = true
            bluetoothService?.write(DEAD.toByteArray())
            checkWin()
        }

        for (y in (0 until blocks.size))
        {
            for (x in (0 until blocks[0].size))
            {
                if (ball.collision(blocks[y][x]) && blocks[y][x].isVisible())
                {
                    if (ball.collisionSide(blocks[y][x]))
                        ball.invX()
                    else
                        ball.invY()

                    blocks[y][x].hide()
                    player1.score += 1
                    bluetoothService?.write(ADD.toByteArray())
                }
            }
        }
    }

    private fun checkWin()
    {
        if (player1.score > player2.score)
        {
            gameStatus(WIN)
            if (!winner)
                bluetoothService?.write(LOST.toByteArray())
        }
        else if (player1.score < player2.score)
        {
            gameStatus(LOST)
            if (!winner)
                bluetoothService?.write(WIN.toByteArray())
        }
        else
        {
            gameStatus(TIE)
            if (!winner)
                bluetoothService?.write(TIE.toByteArray())
        }
        winner = true
    }

    private fun drawStick(p : Paint, c : android.graphics.Canvas)
    {
        if (!dead)
            sticks[0].draw(p, c, sticks[0].getX(), sticks[0].getY())
        else
            checkWin()
    }

    fun gameStatus(s: String)
    {
        p?.let { paint ->
            c?.let { canvas ->
                when(s)
                {
                    LOST -> {
                        paint.textSize = 200f
                        paint.color = Color.WHITE
                        canvas.drawText("Perdiste", canvas.width / 2f - canvas.width * 0.30f, canvas.height / 2f, paint)

                        for (stick in (0 until sticks.size))
                        {
                            sticks[stick].draw(paint, canvas, sticks[0].getX(), sticks[0].getY())
                        }
                    }
                    WIN -> {
                        paint.textSize = 200f
                        paint.color = Color.WHITE
                        canvas.drawText("Ganaste", canvas.width / 2f - canvas.width * 0.30f, canvas.height / 2f, paint)
                        win = true
                    }
                    TIE -> {
                        paint.textSize = 200f
                        paint.color = Color.WHITE
                        canvas.drawText("EMPATE", canvas.width / 2f - canvas.width * 0.30f, canvas.height / 2f, paint)
                        win = true
                    }
                }
            }
        }
    }

    private fun drawBlocks(p: Paint, c: android.graphics.Canvas)
    {
        for (y in (0 until blocks.size))
        {
            for (x in (0 until blocks[0].size))
            {
                if (blocks[y][x].isVisible())
                    blocks[y][x].draw(p, c, blocks[y][x].getX(), blocks[y][x].getY())
            }
        }
    }

    private fun getBlocks(canvas: android.graphics.Canvas)
    {
        blocks = ArrayList()
        var x = 20f
        var y = 200f
        for (block in sprites.getBricks())
        {
            val row = ArrayList<GameObject>()

            for (i in (0 until canvas.width) step block.width)
            {
                if (i + block.width < canvas.width)
                {
                    row.add(GameObject(Block(block, block.width, block.height, true, x, y)))
                    x += block.width
                }
            }
            y += block.height

            blocks.add(row)
            x = 20f
        }
    }

    private fun getBall(canvas: android.graphics.Canvas)
    {
        ball = GameObject(Ball(sprites.ball, sprites.ball.width, sprites.ball.height, canvas))
    }

    private fun getStick(canvas: android.graphics.Canvas)
    {
        sticks = ArrayList()
        val spritesList =
            if (type == SERVER)
                sprites.getPlayer1()
            else
                sprites.getPlayer2()

        for (s in spritesList)
        {
            sticks.add(GameObject(Stick(s, s.width, s.height, canvas.width / 2f - s.width / 2,
                                        canvas.height - canvas.height * 0.20f)))
        }
    }

    private fun loadAssets()
    {
        sprites.loadSprites()
    }

    fun event(event : MotionEvent)
    {
        when (event.action)
        {
            MotionEvent.ACTION_DOWN -> {
                if (dead)
                {
                    reset()
                    bluetoothService?.write(RESET.toByteArray())
                }

                if (!start)
                {
                    bluetoothService?.write(START.toByteArray())
                    start = true
                }

                if (sticks[0].touchIt(event) && !dead)
                {
                    stick = sticks[0]
                }
            }
            MotionEvent.ACTION_MOVE -> {
                stick?.let {
                    if (!dead)
                        sticks[0].setX(event.x - sticks[0].getWidth() / 2)
                }
            }
            MotionEvent.ACTION_UP -> {
                stick = null
            }
        }
    }

    fun reset()
    {
        start = true
        dead = false
        player1.score = 0
        player2.score = 0
        win = false
        firstTime = true
        winner = false
    }


    /**********************BLUETOOTH****************************/
    private fun startBluetoothService()
    {
        Socket.socket?.let { socket ->
            bluetoothService = BluetoothService(socket, this)
            bluetoothService?.start()
        }
    }
}