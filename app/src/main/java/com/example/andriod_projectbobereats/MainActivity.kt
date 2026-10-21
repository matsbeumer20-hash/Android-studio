package com.example.andriod_projectbobereats

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
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

        // Klik op Umi Sushi Bar kaart om naar MainActivity4 te gaan
        val cardUmiSushi = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.cardUmiSushi)
        cardUmiSushi?.setOnClickListener {
            val intent = Intent(this, MainActivity4::class.java)
            startActivity(intent)
        }

        // Onderste navigatiebalk logica
        val btnNavSearch = findViewById<LinearLayout>(R.id.btnNavSearch)
        btnNavSearch?.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        val btnNavCart = findViewById<LinearLayout>(R.id.btnNavCart)
        btnNavCart?.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }
    }
}