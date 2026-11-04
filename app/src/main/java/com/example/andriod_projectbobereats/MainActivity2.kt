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

class MainActivity2 : AppCompatActivity() {

    private var isMenuOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPreferences = getSharedPreferences("BoberPrefs", Context.MODE_PRIVATE)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
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
                viewOverlay?.animate()?.alpha(0f)?.duration = 300
                layoutSideMenu?.postDelayed({
                    if (!isMenuOpen) {
                        layoutSideMenu?.visibility = View.GONE
                        viewOverlay?.visibility = View.GONE
                    }
                }, 300)
            }
        }

        // Klik op de menuknop (zonder vervelende toast melding)
        btnBack?.setOnClickListener {
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

        // Navigatie naar andere schermen
        findViewById<ConstraintLayout>(R.id.cardResultUmi)?.setOnClickListener {
            startActivity(Intent(this, MainActivity4::class.java))
        }

        findViewById<TextView>(R.id.catSushiSearch)?.setOnClickListener {
            startActivity(Intent(this, MainActivity5::class.java))
        }

        findViewById<LinearLayout>(R.id.btnNavHome2)?.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.btnNavCart2)?.setOnClickListener {
            startActivity(Intent(this, MainActivity3::class.java))
        }
    }
}