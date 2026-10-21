package com.example.andriod_projectbobereats

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity4 : AppCompatActivity() {

    private var count1 = 1
    private var count2 = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main4)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- Gerecht 1 Logica (Dragon Roll) ---
        val tvCount1 = findViewById<TextView>(R.id.tvCount1)
        val btnMinus1 = findViewById<Button>(R.id.btnMinus1)
        val btnPlus1 = findViewById<Button>(R.id.btnPlus1)
        val btnAddDish1 = findViewById<Button>(R.id.btnAddDish1)

        btnPlus1?.setOnClickListener {
            if (count1 < 99) {
                count1++
                tvCount1?.text = count1.toString()
            }
        }

        btnMinus1?.setOnClickListener {
            if (count1 > 1) {
                count1--
                tvCount1?.text = count1.toString()
            }
        }

        btnAddDish1?.setOnClickListener {
            for (i in 0 until count1) {
                CartManager.addItem(CartItem("Dragon Roll (8 stuks)", 14.50))
            }
            Toast.makeText(this, "${count1}x Dragon Roll toegevoegd!", Toast.LENGTH_SHORT).show()
        }

        // --- Gerecht 2 Logica (Salmon California Roll) ---
        val tvCount2 = findViewById<TextView>(R.id.tvCount2)
        val btnMinus2 = findViewById<Button>(R.id.btnMinus2)
        val btnPlus2 = findViewById<Button>(R.id.btnPlus2)
        val btnAddDish2 = findViewById<Button>(R.id.btnAddDish2)

        btnPlus2?.setOnClickListener {
            if (count2 < 99) {
                count2++
                tvCount2?.text = count2.toString()
            }
        }

        btnMinus2?.setOnClickListener {
            if (count2 > 1) {
                count2--
                tvCount2?.text = count2.toString()
            }
        }

        btnAddDish2?.setOnClickListener {
            for (i in 0 until count2) {
                CartManager.addItem(CartItem("Salmon California Roll (8 stuks)", 12.00))
            }
            Toast.makeText(this, "${count2}x Salmon California Roll toegevoegd!", Toast.LENGTH_SHORT).show()
        }

        // Onderste navigatiebalk
        val btnNavHome = findViewById<LinearLayout>(R.id.btnNavHome4)
        btnNavHome?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val btnNavSearch = findViewById<LinearLayout>(R.id.btnNavSearch4)
        btnNavSearch?.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        val btnNavCart = findViewById<LinearLayout>(R.id.btnNavCart4)
        btnNavCart?.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }
    }
}