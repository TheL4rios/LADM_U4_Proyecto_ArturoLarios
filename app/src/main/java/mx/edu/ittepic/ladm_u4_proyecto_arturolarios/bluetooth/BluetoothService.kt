package mx.edu.ittepic.ladm_u4_proyecto_arturolarios.bluetooth

import android.bluetooth.BluetoothSocket
import android.util.Log
import mx.edu.ittepic.ladm_u4_proyecto_arturolarios.common.Game
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

const val START = "START"
const val ADD = "ADD"
const val DEAD = "DEAD"
const val LOST = "LOST"
const val WIN = "WIN"
const val TIE = "TIE"
const val RESET = "RESET"

class BluetoothService(private val mmSocket: BluetoothSocket, private val game : Game) : Thread() {

    private val mmInStream: InputStream = mmSocket.inputStream
    private val mmOutStream: OutputStream = mmSocket.outputStream

    override fun run() {
        while (true) {
            if (mmInStream.available() > 0)
            {
                val available = mmInStream.available()
                val bytes = ByteArray(available)
                mmInStream.read(bytes, 0, available)
                val text = String(bytes)

                analyzeText(text)
            }
        }
    }

    private fun analyzeText(text: String)
    {
        when(text)
        {
            START -> game.start = true
            ADD -> game.player2.score++
            DEAD -> game.dead = true
            LOST -> game.gameStatus(text)
            WIN -> game.gameStatus(text)
            TIE -> game.gameStatus(text)
            RESET -> game.reset()
        }
    }

    fun write(bytes: ByteArray) {
        try {
            mmOutStream.write(bytes)
        } catch (e: IOException) {
            return
        }
    }

    fun cancel() {
        try {
            mmSocket.close()
        } catch (e: IOException) {
            Log.e("e", "Could not close the connect socket", e)
        }
    }
}
