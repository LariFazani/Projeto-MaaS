package br.com.fiap.projetomaas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FinanceiroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_financeiro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnCancela = findViewById<Button>(R.id.btnCancelarPerfil)

        btnCancela.setOnClickListener {
            val i = Intent(this, InicioActivity::class.java)
            startActivity(i)
        }

        val imageInicio = findViewById<ImageView>(R.id.image1)
        val imageQRCode = findViewById<ImageView>(R.id.image3)
        val imagePerfil = findViewById<ImageView>(R.id.image4Perfil)

        imageInicio.setOnClickListener {
            val i = Intent(this, FinanceiroActivity::class.java)
            startActivity(i)
        }

        imageQRCode.setOnClickListener{
            val i3 = Intent(this, QRCodeActivity::class.java)
            startActivity(i3)
        }

        imagePerfil.setOnClickListener {
            val i4 = Intent(this, PerfilActivity::class.java)
            startActivity(i4)
        }
    }
}