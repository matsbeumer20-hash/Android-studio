package com.example.andriod_projectbobereats

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main5)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Klik op Umi Sushi Bar -> Ga naar MainActivity4
        val cardUmiSushiCategory = findViewById<ConstraintLayout>(R.id.cardUmiSushiCategory)
        cardUmiSushiCategory?.setOnClickListener {
            startActivity(Intent(this, MainActivity4::class.java))
        }

        // Onderste navigatiebalk
        findViewById<LinearLayout>(R.id.btnNavHome5)?.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.btnNavSearch5)?.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }

        findViewById<LinearLayout>(R.id.btnNavCart5)?.setOnClickListener {
            startActivity(Intent(this, MainActivity3::class.java))
        }
    }
}