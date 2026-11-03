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

class MainActivity6 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main6)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        btnRegister?.setOnClickListener {
            val name = etName?.text.toString().trim()
            val email = etEmail?.text.toString().trim()

            if (name.isNotEmpty() && email.isNotEmpty()) {
                // Sla de accountgegevens lokaal op (SharedPreferences)
                val sharedPreferences = getSharedPreferences("BoberPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("USER_NAME", name)
                editor.putString("USER_EMAIL", email)
                editor.apply()

                Toast.makeText(this, "Welkom $name! Account aangemaakt en ingelogd.", Toast.LENGTH_LONG).show()

                // Terug naar Home
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Vul alstublieft alle velden in.", Toast.LENGTH_SHORT).show()
            }
        }

        // Onderste navigatiebalk
        findViewById<LinearLayout>(R.id.btnNavHome6)?.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.btnNavSearch6)?.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }

        findViewById<LinearLayout>(R.id.btnNavCart6)?.setOnClickListener {
            startActivity(Intent(this, MainActivity3::class.java))
        }
    }
}