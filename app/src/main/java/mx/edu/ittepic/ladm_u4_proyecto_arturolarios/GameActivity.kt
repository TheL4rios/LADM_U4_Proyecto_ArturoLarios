package mx.edu.ittepic.ladm_u4_proyecto_arturolarios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.edu.ittepic.ladm_u4_proyecto_arturolarios.canvas.Canvas

const val SERVER = "server"

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extras = intent.extras
        val type = extras?.getString("type") ?: ""
        setContentView(Canvas(this, type))
    }
}
