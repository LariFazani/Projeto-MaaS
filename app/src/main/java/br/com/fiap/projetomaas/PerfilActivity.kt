package br.com.fiap.projetomaas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_perfil)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imageInicio = findViewById<ImageView>(R.id.image1)
        val imageFinanceiro = findViewById<ImageView>(R.id.image2)
        val imageQRCode = findViewById<ImageView>(R.id.image3)

        imageInicio.setOnClickListener {
            val i = Intent(this, FinanceiroActivity::class.java)
            startActivity(i)
        }

        imageFinanceiro.setOnClickListener{
            val i2 = Intent(this, FinanceiroActivity::class.java)
            startActivity(i2)
        }

        imageQRCode.setOnClickListener{
            val i3 = Intent(this, QRCodeActivity::class.java)
            startActivity(i3)
        }

        val btnCancela = findViewById<Button>(R.id.btnCancelarPerfil)

        btnCancela.setOnClickListener {
            val i = Intent(this, InicioActivity::class.java)
            startActivity(i)
        }

        val db = DataBaseManager(this)

        val sharedPrefs = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val userEmail = sharedPrefs.getString("user_email", null)

        if(userEmail !=null) {
            val cursor = db.getUserByEmail(userEmail)

            if (cursor != null && cursor.moveToFirst()) {
                val name = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseManager.COLUMN_NAME))
                val email = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseManager.COLUMN_EMAIL))
                val mobility = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseManager.COLUMN_MOBILITY)) == 1
                val bus = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseManager.COLUMN_BUS)) == 1
                val metro = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseManager.COLUMN_METRO)) == 1
                val train = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseManager.COLUMN_TRAIN)) == 1
                val car = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseManager.COLUMN_CAR)) == 1
                val scooter = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseManager.COLUMN_SCOOTER)) == 1
                val bike = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseManager.COLUMN_BIKE)) == 1

                findViewById<EditText>(R.id.txtNome).setText(name)
                findViewById<EditText>(R.id.txtEmail).setText(email)
                findViewById<CheckBox>(R.id.checkbox).isChecked = mobility
                findViewById<CheckBox>(R.id.bus).isChecked = bus
                findViewById<CheckBox>(R.id.metro).isChecked = metro
                findViewById<CheckBox>(R.id.trem).isChecked = train
                findViewById<CheckBox>(R.id.carro).isChecked = car
                findViewById<CheckBox>(R.id.Patinete).isChecked = scooter
                findViewById<CheckBox>(R.id.bicicleta).isChecked = bike

                cursor.close()
            } else {
                Toast.makeText(this, "Dados não encontrados.", Toast.LENGTH_LONG).show()
            }

            findViewById<Button>(R.id.btnSalvarPerfil).setOnClickListener {
                val name = findViewById<EditText>(R.id.txtNome).text.toString()
                val email = findViewById<EditText>(R.id.txtEmail).text.toString()
                val mobility = findViewById<CheckBox>(R.id.checkbox).isChecked
                val bus = findViewById<CheckBox>(R.id.bus).isChecked
                val metro = findViewById<CheckBox>(R.id.metro).isChecked
                val train = findViewById<CheckBox>(R.id.trem).isChecked
                val car = findViewById<CheckBox>(R.id.carro).isChecked
                val scooter = findViewById<CheckBox>(R.id.Patinete).isChecked
                val bike = findViewById<CheckBox>(R.id.bicicleta).isChecked


                if (userEmail != null) {
                    db.updateUser(
                        name,
                        email,
                        if (mobility) 1 else 0,
                        if (bus) 1 else 0,
                        if (metro) 1 else 0,
                        if (train) 1 else 0,
                        if (car) 1 else 0,
                        if (scooter) 1 else 0,
                        if (bike) 1 else 0,
                    )
                    Toast.makeText(this, "Perfil atualizado com sucesso!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Erro ao atualizar perfil.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}