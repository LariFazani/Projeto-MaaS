package br.com.fiap.projetomaas

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity


class QRCodeActivity : AppCompatActivity() {

    private lateinit var txtContagem: TextView
    private var contador = 60
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_qrcode)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtContagem = findViewById(R.id.txtContagem)
        // Inicia a contagem regressiva
        startContagem()

        //Menu Inferior
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

    private fun startContagem() {
        handler.post(object : Runnable {
            override fun run() {
                txtContagem.text = contador.toString()
                contador--

                if (contador < 0) {
                    contador = 60
                }

                handler.postDelayed(this, 1000) // Executa a cada 1 segundo
            }
        })
    }
}