package com.example.andriod_projectbobereats

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Klik op de Umi Sushi Bar kaart -> Ga naar MainActivity4
        val cardResultUmi = findViewById<ConstraintLayout>(R.id.cardResultUmi)
        cardResultUmi?.setOnClickListener {
            val intent = Intent(this, MainActivity4::class.java)
            startActivity(intent)
        }

        // Klik op de Sushi categorie in de zoekbalk -> Ga naar MainActivity5
        val catSushiSearch = findViewById<TextView>(R.id.catSushiSearch)
        catSushiSearch?.setOnClickListener {
            val intent = Intent(this, MainActivity5::class.java)
            startActivity(intent)
        }

        // Onderste navigatiebalk
        val btnNavHome = findViewById<LinearLayout>(R.id.btnNavHome2)
        btnNavHome?.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val btnNavSearch = findViewById<LinearLayout>(R.id.btnNavSearch2)
        btnNavSearch?.setOnClickListener {
            // Al op zoekpagina
        }

        val btnNavCart = findViewById<LinearLayout>(R.id.btnNavCart2)
        btnNavCart?.setOnClickListener {
            startActivity(Intent(this, MainActivity3::class.java))
        }
    }
}