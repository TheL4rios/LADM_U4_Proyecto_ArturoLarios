package mx.edu.ittepic.ladm_u4_proyecto_arturolarios.bluetooth

import android.bluetooth.BluetoothSocket

object Socket {
    @get:Synchronized
    @set:Synchronized
    var socket: BluetoothSocket? = null
}