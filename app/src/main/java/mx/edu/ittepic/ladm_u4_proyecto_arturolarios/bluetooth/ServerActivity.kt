package mx.edu.ittepic.ladm_u4_proyecto_arturolarios.bluetooth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_server.*
import mx.edu.ittepic.ladm_u4_proyecto_arturolarios.R

class ServerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_server)

        btnStart.setOnClickListener {
            Server(this).start()
            txt.text = "Esperando conexión..."
            progressBar.visibility = View.VISIBLE
        }
    }
}
