package com.example.andriod_projectbobereats

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var isMenuOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Check of de gebruiker is ingelogd via SharedPreferences
        val sharedPreferences = getSharedPreferences("BoberPrefs", Context.MODE_PRIVATE)
        val savedName = sharedPreferences.getString("USER_NAME", null)

        val tvPromoTitle = findViewById<TextView>(R.id.tvPromoTitle)
        if (savedName != null) {
            tvPromoTitle?.text = "Welkom terug, $savedName!"
        } else {
            tvPromoTitle?.text = "Hallo, Gast"
        }

        // Elementen ophalen voor het zijmenu
        val btnMenu = findViewById<ImageView>(R.id.btnMenu)
        val layoutSideMenu = findViewById<LinearLayout>(R.id.layoutSideMenu)
        val viewOverlay = findViewById<View>(R.id.viewOverlay)

        // Functie om het menu te openen of te sluiten
        fun toggleMenu() {
            isMenuOpen = !isMenuOpen
            if (isMenuOpen) {
                layoutSideMenu.visibility = View.VISIBLE
                viewOverlay.visibility = View.VISIBLE
                layoutSideMenu.animate().translationX(0f).duration = 300
                viewOverlay.animate().alpha(1f).duration = 300
            } else {
                layoutSideMenu.animate().translationX(-300f).duration = 300
                viewOverlay.animate().alpha(0f).duration = 0
                layoutSideMenu.postDelayed({
                    if (!isMenuOpen) {
                        layoutSideMenu.visibility = View.GONE
                        viewOverlay.visibility = View.GONE
                    }
                }, 300)
            }
        }

        // Klik op de menuknop linksboven
        btnMenu?.setOnClickListener {
            toggleMenu()
        }

        // Klik op de donkere overlay sluit het menu weer
        viewOverlay?.setOnClickListener {
            toggleMenu()
        }

        // Klik op Inloggen -> Ga naar MainActivity7
        findViewById<TextView>(R.id.menuLogin)?.setOnClickListener {
            startActivity(Intent(this, MainActivity7::class.java))
            toggleMenu()
        }

        // Klik op Account aanmaken -> Ga naar MainActivity6
        findViewById<TextView>(R.id.menuAccount)?.setOnClickListener {
            startActivity(Intent(this, MainActivity6::class.java))
            toggleMenu()
        }

        // Klik op Bober pas
        findViewById<TextView>(R.id.menuBoberPas)?.setOnClickListener {
            Toast.makeText(this, "Bober pas aangeklikt", Toast.LENGTH_SHORT).show()
            toggleMenu()
        }

        // Klik op Uitloggen: wist de opgeslagen gegevens
        findViewById<TextView>(R.id.menuLogout)?.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            Toast.makeText(this, "Succesvol uitgelogd", Toast.LENGTH_SHORT).show()
            recreate() // Herstart de activiteit om de naam te resetten naar Gast
        }

        // Klik op Umi Sushi Bar kaart (cardRestaurant1) -> Ga naar MainActivity4
        val cardRestaurant1 = findViewById<ConstraintLayout>(R.id.cardRestaurant1)
        cardRestaurant1?.setOnClickListener {
            val intent = Intent(this, MainActivity4::class.java)
            startActivity(intent)
        }

        // Onderste navigatiebalk: Zoeken
        val btnNavSearch1 = findViewById<LinearLayout>(R.id.btnNavSearch1)
        btnNavSearch1?.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        // Onderste navigatiebalk: Winkelmand
        val btnNavCart1 = findViewById<LinearLayout>(R.id.btnNavCart1)
        btnNavCart1?.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }
    }
}