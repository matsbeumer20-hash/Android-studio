package com.example.andriod_projectbobereats

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity8 : AppCompatActivity() {

    private var isSheetOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main8)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnBuyPass = findViewById<Button>(R.id.btnBuyPass)
        val layoutBottomSheet = findViewById<LinearLayout>(R.id.layoutBottomSheet)
        val viewOverlay8 = findViewById<View>(R.id.viewOverlay8)
        val btnCloseSheet = findViewById<Button>(R.id.btnCloseSheet)

        // Tekstweergave binnen het uitschuifbare paneel om de titel/tekst aan te passen
        val tvSheetTitle = layoutBottomSheet.findViewById<TextView>(R.id.tvSheetTitle)
        val tvSheetDesc = layoutBottomSheet.findViewById<TextView>(R.id.tvSheetDesc)

        // SharedPreferences om te onthouden of de pas al is gekocht
        val sharedPreferences = getSharedPreferences("BoberPrefs", Context.MODE_PRIVATE)

        // Functie om het onderste paneel in- of uit te schuiven
        fun toggleBottomSheet() {
            isSheetOpen = !isSheetOpen
            if (isSheetOpen) {
                layoutBottomSheet.visibility = View.VISIBLE
                viewOverlay8.visibility = View.VISIBLE
                layoutBottomSheet.animate().translationY(0f).duration = 300
                viewOverlay8.animate().alpha(1f).duration = 300
            } else {
                layoutBottomSheet.animate().translationY(1000f).duration = 300
                viewOverlay8.animate().alpha(0f).duration = 300
                layoutBottomSheet.postDelayed({
                    if (!isSheetOpen) {
                        layoutBottomSheet.visibility = View.GONE
                        viewOverlay8.visibility = View.GONE
                    }
                }, 300)
            }
        }

        // Klik op "Bober Pas Kopen"
        btnBuyPass?.setOnClickListener {
            val hasPass = sharedPreferences.getBoolean("HAS_BOBER_PASS", false)

            if (hasPass) {
                // Als de gebruiker de pas al heeft: pas de tekst aan en open het paneel
                tvSheetTitle?.text = "✨ Je hebt de Bober Pas al!"
                tvSheetDesc?.text = "Je abonnement is al actief. Je geniet al van gratis bezorging en alle exclusieve voordelen!"
            } else {
                // Eerste keer kopen: Sla op in SharedPreferences en toon succesbericht
                val editor = sharedPreferences.edit()
                editor.putBoolean("HAS_BOBER_PASS", true)
                editor.apply()

                tvSheetTitle?.text = "🎉 Bedankt voor je aanmelding!"
                tvSheetDesc?.text = "Je Bober Pas is succesvol geactiveerd. Je geniet nu direct van gratis bezorging en al je voordelen!"
            }

            toggleBottomSheet()
        }

        // Sluit knop en overlay sluiten het paneel weer
        btnCloseSheet?.setOnClickListener {
            toggleBottomSheet()
        }

        viewOverlay8?.setOnClickListener {
            toggleBottomSheet()
        }

        // Onderste navigatiebalk
        findViewById<LinearLayout>(R.id.btnNavHome8)?.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.btnNavSearch8)?.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }

        findViewById<LinearLayout>(R.id.btnNavCart8)?.setOnClickListener {
            startActivity(Intent(this, MainActivity3::class.java))
        }
    }
}