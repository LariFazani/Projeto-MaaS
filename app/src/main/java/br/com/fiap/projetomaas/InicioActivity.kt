package br.com.fiap.projetomaas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inicio)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Menu Inferior
        val imageFinanceiro = findViewById<ImageView>(R.id.image2)
        val imageQRCode = findViewById<ImageView>(R.id.image3)
        val imagePerfil = findViewById<ImageView>(R.id.image4Perfil)

        imageFinanceiro.setOnClickListener{
            val i2 = Intent(this, FinanceiroActivity::class.java)
            startActivity(i2)
        }

        imageQRCode.setOnClickListener{
            val i3 = Intent(this, QRCodeActivity::class.java)
            startActivity(i3)
        }

        imagePerfil.setOnClickListener{
            val i4 = Intent(this, PerfilActivity::class.java)
            startActivity(i4)
        }
    }
}