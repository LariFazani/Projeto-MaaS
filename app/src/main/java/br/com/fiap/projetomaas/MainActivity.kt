package br.com.fiap.projetomaas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtEmail = findViewById<EditText>(R.id.txtEmail)
        val txtSenha = findViewById<EditText>(R.id.txtSenha)
        val btnEsqueciSenha = findViewById<Button>(R.id.btnEsqueciSenha)
        val btnProsseguir = findViewById<Button>(R.id.btnProsseguir)
        val bntCadastro = findViewById<Button>(R.id.btnCadastro)

        btnEsqueciSenha.setOnClickListener {
            mostrarModalEsqueciSenha()
        }

        btnProsseguir.setOnClickListener {
            val db = DataBaseManager(this)
            val email = txtEmail.text.toString().trim()
            val senha = txtSenha.text.toString().trim()
            val i = Intent(this, InicioActivity::class.java)

            if(db.checkUser(email, senha)) {
                Toast.makeText(this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show()
                val sharedPrefs = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                sharedPrefs.edit().putString("user_email",email).apply()
                startActivity(i)

            } else {
               Toast.makeText(this, "Email ou senha inválidos.", Toast.LENGTH_SHORT).show()
            }
        }

        bntCadastro.setOnClickListener {
            val i = Intent(this, CadastroActivity::class.java)
            startActivity(i)
        }



    }



    private fun mostrarModalEsqueciSenha() {
        val caixaDialogo = LayoutInflater.from(this).inflate(R.layout.esqueci_senha,null)
        val txtEmailEsqueci = caixaDialogo.findViewById<EditText>(R.id.etEmail)

        val dialogo = AlertDialog.Builder(this)
            .setView(caixaDialogo)
            .setTitle("Recuperar Senha")
            .setCancelable(false)
            .create()

        caixaDialogo.findViewById<Button>(R.id.btnEnviarEsqueciSenha).setOnClickListener {
            val email = txtEmailEsqueci.text.toString().trim()

            if(email.isNotEmpty()) {
                Toast.makeText(this, "Email enviado", Toast.LENGTH_SHORT).show()
                dialogo.dismiss()
            }else {
                txtEmailEsqueci.error = "Por favor, insira um email válido"
            }
        }

        caixaDialogo.findViewById<Button>(R.id.btnCancelarEsqueciSenha).setOnClickListener {
            dialogo.dismiss()
        }

        dialogo.show()
    }


}