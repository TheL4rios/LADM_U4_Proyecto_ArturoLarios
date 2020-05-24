package mx.edu.ittepic.ladm_u4_proyecto_arturolarios.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.util.Log
import mx.edu.ittepic.ladm_u4_proyecto_arturolarios.GameActivity
import java.io.IOException
import java.util.*
import java.util.UUID.fromString

class Server(private val server : ServerActivity) : Thread() {

    object Bluetooth{
        const val NAME = "ARKANOID"
        val UUID : UUID = fromString("df7a8494-a72d-4a4c-8995-6aca06ff4b06")
    }

    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()

    private val mmServerSocket: BluetoothServerSocket? by lazy(LazyThreadSafetyMode.NONE) {
        bluetoothAdapter?.listenUsingInsecureRfcommWithServiceRecord(
            Bluetooth.NAME,
            Bluetooth.UUID
        )
    }

    override fun run() {
        var shouldLoop = true
        while (shouldLoop) {
            val socket: BluetoothSocket? = try {
                mmServerSocket?.accept()
            } catch (e: IOException) {
                shouldLoop = false
                null
            }
            socket?.let {
                manageMyConnectedSocket(it)
                mmServerSocket?.close()
                shouldLoop = false
            }
        }
    }

    private fun manageMyConnectedSocket(socket: BluetoothSocket)
    {
        Socket.socket = socket
        server.startActivity(Intent(server, GameActivity :: class.java).putExtra("type", "server"))
    }

    fun cancel() {
        try {
            mmServerSocket?.close()
        } catch (e: IOException) {
            Log.e("e", "Could not close the connect socket", e)
        }
    }
}