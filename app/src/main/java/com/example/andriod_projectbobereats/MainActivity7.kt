package com.example.andriod_projectbobereats

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity7 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main7)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etLoginEmail = findViewById<EditText>(R.id.etLoginEmail)
        val etLoginPassword = findViewById<EditText>(R.id.etLoginPassword)
        val btnLoginSubmit = findViewById<Button>(R.id.btnLoginSubmit)

        btnLoginSubmit?.setOnClickListener {
            val emailInput = etLoginEmail?.text.toString().trim()
            val passwordInput = etLoginPassword?.text.toString().trim()

            if (emailInput.isEmpty() || passwordInput.isEmpty()) {
                Toast.makeText(this, "Vul alle velden in.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Haal opgeslagen gegevens op
            val sharedPreferences = getSharedPreferences("BoberPrefs", Context.MODE_PRIVATE)
            val registeredEmail = sharedPreferences.getString("USER_EMAIL", null)
            val registeredPassword = sharedPreferences.getString("USER_PASSWORD", null)
            val registeredName = sharedPreferences.getString("USER_NAME", "Gebruiker")

            // Controleer of er überhaupt een account bestaat
            if (registeredEmail == null || registeredPassword == null) {
                Toast.makeText(this, "Geen account gevonden. Maak eerst een account aan!", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, MainActivity6::class.java))
                finish()
                return@setOnClickListener
            }

            // Controleer of e-mail én wachtwoord kloppen
            if (emailInput.equals(registeredEmail, ignoreCase = true) && passwordInput == registeredPassword) {
                Toast.makeText(this, "Welkom terug, $registeredName!", Toast.LENGTH_LONG).show()

                // Ga naar de homepagina
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Onjuist e-mailadres of wachtwoord.", Toast.LENGTH_SHORT).show()
            }
        }

        // Onderste navigatiebalk
        findViewById<LinearLayout>(R.id.btnNavHome7)?.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.btnNavSearch7)?.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }

        findViewById<LinearLayout>(R.id.btnNavCart7)?.setOnClickListener {
            startActivity(Intent(this, MainActivity3::class.java))
        }
    }
}