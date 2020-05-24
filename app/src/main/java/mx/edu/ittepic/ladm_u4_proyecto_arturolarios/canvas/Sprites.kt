package mx.edu.ittepic.ladm_u4_proyecto_arturolarios.canvas

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import mx.edu.ittepic.ladm_u4_proyecto_arturolarios.R
import mx.edu.ittepic.ladm_u4_proyecto_arturolarios.SERVER

class Sprites(private val canvas : Canvas, private val type : String)
{
    lateinit var ball : Bitmap

    lateinit var brickBlue : Bitmap
    lateinit var brickCyan : Bitmap
    lateinit var brickGold : Bitmap
    lateinit var brickGreen : Bitmap
    lateinit var brickOrange : Bitmap
    lateinit var brickPink : Bitmap
    lateinit var brickRed : Bitmap
    lateinit var brickSilver : Bitmap

    lateinit var player1 : Bitmap
    lateinit var player1Dead1 : Bitmap
    lateinit var player1Dead2 : Bitmap
    lateinit var player1Dead3 : Bitmap
    lateinit var player1Dead4 : Bitmap
    lateinit var player1Dead5 : Bitmap
    lateinit var player1Dead6 : Bitmap

    lateinit var player2 : Bitmap
    lateinit var player2Dead1 : Bitmap
    lateinit var player2Dead2 : Bitmap
    lateinit var player2Dead3 : Bitmap
    lateinit var player2Dead4 : Bitmap
    lateinit var player2Dead5 : Bitmap
    lateinit var player2Dead6 : Bitmap

    lateinit var background : Bitmap

    fun loadSprites()
    {
        background = BitmapFactory.decodeResource(canvas.resources, R.drawable.hexagon_pattern)
        ball = BitmapFactory.decodeResource(canvas.resources, R.drawable.ball)

        brickBlue = BitmapFactory.decodeResource(canvas.resources, R.drawable.brick_blue)
        brickCyan = BitmapFactory.decodeResource(canvas.resources, R.drawable.brick_cyan)
        brickGold = BitmapFactory.decodeResource(canvas.resources, R.drawable.brick_gold)
        brickGreen = BitmapFactory.decodeResource(canvas.resources, R.drawable.brick_green)
        brickOrange = BitmapFactory.decodeResource(canvas.resources, R.drawable.brick_orange)
        brickPink = BitmapFactory.decodeResource(canvas.resources, R.drawable.brick_pink)
        brickRed = BitmapFactory.decodeResource(canvas.resources, R.drawable.brick_red)
        brickSilver = BitmapFactory.decodeResource(canvas.resources, R.drawable.brick_silver)

        if (type == SERVER)
        {
            player1 = BitmapFactory.decodeResource(canvas.resources, R.drawable.player1)
            player1Dead1 = BitmapFactory.decodeResource(canvas.resources, R.drawable.player1_dead_1)
            player1Dead2 = BitmapFactory.decodeResource(canvas.resources, R.drawable.player1_dead_2)
            player1Dead3 = BitmapFactory.decodeResource(canvas.resources, R.drawable.player1_dead_3)
            player1Dead4 = BitmapFactory.decodeResource(canvas.resources, R.drawable.player1_dead_4)
            player1Dead5 = BitmapFactory.decodeResource(canvas.resources, R.drawable.player1_dead_5)
            player1Dead6 = BitmapFactory.decodeResource(canvas.resources, R.drawable.player1_dead_6)
        }
        else
        {
            player2 = BitmapFactory.decodeResource(canvas.resources, R.drawable.player2)
            player2Dead1 = BitmapFactory.decodeResource(canvas.resources, R.drawable.player2_dead_1)
            player2Dead2 = BitmapFactory.decodeResource(canvas.resources, R.drawable.player2_dead_2)
            player2Dead3 = BitmapFactory.decodeResource(canvas.resources, R.drawable.player2_dead_3)
            player2Dead4 = BitmapFactory.decodeResource(canvas.resources, R.drawable.player2_dead_4)
            player2Dead5 = BitmapFactory.decodeResource(canvas.resources, R.drawable.player2_dead_5)
            player2Dead6 = BitmapFactory.decodeResource(canvas.resources, R.drawable.player2_dead_6)
        }
    }

    fun getBricks() : ArrayList<Bitmap>
    {
        val bricks = ArrayList<Bitmap>()
        // bricks.add(brickSilver)
        bricks.add(brickBlue)
        bricks.add(brickCyan)
        // bricks.add(brickGold)
        bricks.add(brickGreen)
        bricks.add(brickOrange)
        bricks.add(brickPink)
        bricks.add(brickRed)

        return bricks
    }

    fun getPlayer1() : ArrayList<Bitmap>
    {
        val player = ArrayList<Bitmap>()
        player.add(player1)
        player.add(player1Dead1)
        player.add(player1Dead2)
        player.add(player1Dead3)
        player.add(player1Dead4)
        player.add(player1Dead5)
        player.add(player1Dead6)

        return player
    }

    fun getPlayer2() : ArrayList<Bitmap>
    {
        val player = ArrayList<Bitmap>()
        player.add(player2)
        player.add(player2Dead1)
        player.add(player2Dead2)
        player.add(player2Dead3)
        player.add(player2Dead4)
        player.add(player2Dead5)
        player.add(player2Dead6)

        return player
    }
}