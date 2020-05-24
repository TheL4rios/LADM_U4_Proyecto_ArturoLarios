package mx.edu.ittepic.ladm_u4_proyecto_arturolarios.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.util.Log
import mx.edu.ittepic.ladm_u4_proyecto_arturolarios.GameActivity
import java.io.IOException

class Client(device: BluetoothDevice, private val client : ClientActivity) : Thread() {

    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()

    private val mmSocket: BluetoothSocket? by lazy(LazyThreadSafetyMode.NONE) {
        device.createRfcommSocketToServiceRecord(Server.Bluetooth.UUID)
    }

    override fun run() {
        bluetoothAdapter?.cancelDiscovery()

        mmSocket?.let { socket ->
            socket.connect()
            manageMyConnectedSocket(socket)
        }
    }

    fun cancel() {
        try {
            mmSocket?.close()
        } catch (e: IOException) {
            Log.e("e", "Could not close the client socket", e)
        }
    }

    private fun manageMyConnectedSocket(socket: BluetoothSocket)
    {
        Socket.socket = socket
        client.startActivity(Intent(client, GameActivity :: class.java).putExtra("type", "client"))
    }
}