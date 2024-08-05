package br.com.fiap.projetomaas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtNomeCadastro = findViewById<EditText>(R.id.txtNomeCadastro)
        val txtEmailCadastro = findViewById<EditText>(R.id.txtEmailCadastro)
        val txtSenhaCadastro = findViewById<EditText>(R.id.txtSenhaCadastro)
        val txtConfirmaSenha = findViewById<EditText>(R.id.txtConfirmeSenha)
        val btnSalvar = findViewById<Button>(R.id.btnSalvar)
        val btnCancela = findViewById<Button>(R.id.btnCancelarCadastro)

        btnCancela.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        btnSalvar.setOnClickListener {
            val db = DataBaseManager(this)
            val nome = txtNomeCadastro.text.toString()
            val email = txtEmailCadastro.text.toString().trim()
            val senha = txtSenhaCadastro.text.toString()
            val confirmaSenha = txtConfirmaSenha.text.toString()
            val i = Intent(this, MainActivity::class.java)

            if(senha.equals(confirmaSenha)){
                db.addUser(nome, email, senha)
                Toast.makeText(this, "Usuário criado com sucesso", Toast.LENGTH_LONG).show()
                startActivity(i)
            }


        }

    }
}