package mx.edu.ittepic.ladm_u4_proyecto_arturolarios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn2Player.setOnClickListener {
            startActivity(Intent(this, ChooseActivity :: class.java))
        }
    }
}
