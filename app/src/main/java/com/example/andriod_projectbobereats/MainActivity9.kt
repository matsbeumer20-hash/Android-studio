package com.example.andriod_projectbobereats

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity9 : AppCompatActivity() {

    private var isMenuOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main9)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPreferences = getSharedPreferences("BoberPrefs", Context.MODE_PRIVATE)

        // Zijmenu elementen koppelen
        val btnBackCheckout = findViewById<ImageView>(R.id.btnBackCheckout)
        val layoutSideMenu = findViewById<LinearLayout>(R.id.layoutSideMenu)
        val viewOverlay = findViewById<View>(R.id.viewOverlay)

        fun toggleMenu() {
            isMenuOpen = !isMenuOpen
            if (isMenuOpen) {
                layoutSideMenu?.visibility = View.VISIBLE
                viewOverlay?.visibility = View.VISIBLE
                layoutSideMenu?.animate()?.translationX(0f)?.duration = 300
                viewOverlay?.animate()?.alpha(1f)?.duration = 300
            } else {
                layoutSideMenu?.animate()?.translationX(-300f)?.duration = 300
                viewOverlay?.animate()?.alpha(0f)?.duration = 0
                layoutSideMenu?.postDelayed({
                    if (!isMenuOpen) {
                        layoutSideMenu?.visibility = View.GONE
                        viewOverlay?.visibility = View.GONE
                    }
                }, 300)
            }
        }

        btnBackCheckout?.setOnClickListener {
            toggleMenu()
        }

        viewOverlay?.setOnClickListener {
            toggleMenu()
        }

        // Menu opties
        findViewById<TextView>(R.id.menuLogin)?.setOnClickListener {
            startActivity(Intent(this, MainActivity7::class.java))
            toggleMenu()
        }

        findViewById<TextView>(R.id.menuAccount)?.setOnClickListener {
            startActivity(Intent(this, MainActivity6::class.java))
            toggleMenu()
        }

        findViewById<TextView>(R.id.menuBoberPas)?.setOnClickListener {
            startActivity(Intent(this, MainActivity8::class.java))
            toggleMenu()
        }

        findViewById<TextView>(R.id.menuLogout)?.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            Toast.makeText(this, "Succesvol uitgelogd", Toast.LENGTH_SHORT).show()
            recreate()
        }

        // Invoer velden en knop om door te gaan naar betalen (MainActivity10)
        val etAddress = findViewById<EditText>(R.id.etAddress)
        val etCity = findViewById<EditText>(R.id.etCity)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val btnProceedToNext = findViewById<Button>(R.id.btnProceedToNext)

        btnProceedToNext?.setOnClickListener {
            val address = etAddress?.text.toString().trim()
            val city = etCity?.text.toString().trim()
            val phone = etPhone?.text.toString().trim()

            if (address.isEmpty() || city.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Vul alle bezorggegevens in alstublieft.", Toast.LENGTH_SHORT).show()
            } else {
                // Gegevens zijn ingevuld, stuur door naar MainActivity10 (Betaling & Overzicht)
                val intent = Intent(this, MainActivity10::class.java)
                startActivity(intent)
            }
        }

        // Onderste navigatiebalk
        findViewById<LinearLayout>(R.id.btnNavHome9)?.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.btnNavSearch9)?.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }

        findViewById<LinearLayout>(R.id.btnNavCart9)?.setOnClickListener {
            startActivity(Intent(this, MainActivity3::class.java))
        }
    }
}