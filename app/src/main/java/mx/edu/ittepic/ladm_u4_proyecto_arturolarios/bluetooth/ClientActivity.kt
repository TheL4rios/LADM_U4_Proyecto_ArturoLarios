package mx.edu.ittepic.ladm_u4_proyecto_arturolarios.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_client.*
import mx.edu.ittepic.ladm_u4_proyecto_arturolarios.R
import java.util.*

class ClientActivity : AppCompatActivity() {

    object Bluetooth{
        const val REQUEST_ENABLE_BT = 10
    }

    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    private var pairedDevicesArray = ArrayList<BluetoothDevice>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)

        if (bluetoothAdapter == null) {
            AlertDialog.Builder(this)
                .setTitle("Atención")
                .setMessage("Su equipo no es compatible con tecnologia Bluetooth")
                .setPositiveButton("OK"){_, _ ->}
                .show()

            return
        }

        if (!bluetoothAdapter.isEnabled) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent,
                Bluetooth.REQUEST_ENABLE_BT
            )
        }

        progressBar.visibility = View.INVISIBLE

        btnRefresh.setOnClickListener {
            pairedDevices()
        }
    }

    private fun pairedDevices()
    {
        pairedDevicesArray = ArrayList()
        val devices = ArrayList<String>()

        val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter?.bondedDevices
        pairedDevices?.forEach { device ->
            devices.add(device.name)
            pairedDevicesArray.add(device)
        }

        this.pairedDevices.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, devices)

        this.pairedDevices.setOnItemClickListener { _, _, position, _ ->
            Client(pairedDevicesArray[position], this).start()
            progressBar.visibility = View.VISIBLE
            paired.text = "Conectando..."
        }
    }
}
