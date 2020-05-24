package mx.edu.ittepic.ladm_u4_proyecto_arturolarios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_bluetooth.*
import mx.edu.ittepic.ladm_u4_proyecto_arturolarios.bluetooth.ClientActivity
import mx.edu.ittepic.ladm_u4_proyecto_arturolarios.bluetooth.ServerActivity

class ChooseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetooth)

        btnServer.setOnClickListener {
            startActivity(Intent(this, ServerActivity:: class.java))
        }

        btnClient.setOnClickListener {
            startActivity(Intent(this, ClientActivity:: class.java))
        }
    }

}
