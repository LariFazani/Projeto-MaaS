package br.com.fiap.projetomaas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QRCodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_qrcode)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imageInicio = findViewById<ImageView>(R.id.image1)
        val imageFinanceiro = findViewById<ImageView>(R.id.image2)
        val imagePerfil = findViewById<ImageView>(R.id.image4Perfil)

        imageInicio.setOnClickListener {
            val i = Intent(this, InicioActivity::class.java)
            startActivity(i)
        }

        imageFinanceiro.setOnClickListener{
            val i2 = Intent(this, FinanceiroActivity::class.java)
            startActivity(i2)
        }

        imagePerfil.setOnClickListener {
            val i4 = Intent(this, PerfilActivity::class.java)
            startActivity(i4)
        }


    }
}