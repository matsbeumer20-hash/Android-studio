package com.example.andriod_projectbobereats

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity10 : AppCompatActivity() {

    private var isMenuOpen = false
    private var isSheetOpen = false
    private var isBoberPassApplied = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main10)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPreferences = getSharedPreferences("BoberPrefs", Context.MODE_PRIVATE)

        // UI Elementen koppelen
        val btnBackPayment = findViewById<ImageView>(R.id.btnBackPayment)
        val layoutSideMenu = findViewById<LinearLayout>(R.id.layoutSideMenu)
        val viewOverlay = findViewById<View>(R.id.viewOverlay)

        val layoutBottomSheetPass = findViewById<LinearLayout>(R.id.layoutBottomSheetPass)
        val tvSheetPassTitle = findViewById<TextView>(R.id.tvSheetPassTitle)
        val tvSheetPassDesc = findViewById<TextView>(R.id.tvSheetPassDesc)
        val btnCloseSheetPass = findViewById<Button>(R.id.btnCloseSheetPass)

        val btnUseBoberPass = findViewById<Button>(R.id.btnUseBoberPass)
        val tvSubtotal = findViewById<TextView>(R.id.tvSubtotal)
        val tvDeliveryCost = findViewById<TextView>(R.id.tvDeliveryCost)
        val tvFinalTotal = findViewById<TextView>(R.id.tvFinalTotal)
        val btnPlaceOrder = findViewById<Button>(R.id.btnPlaceOrder)

        // Prijzen berekenen via CartManager
        val subtotal = CartManager.getTotalPrice()
        var deliveryFee = 2.99

        fun updatePrices() {
            tvSubtotal?.text = "€${String.format("%.2f", subtotal)}"
            if (isBoberPassApplied) {
                tvDeliveryCost?.text = "€0,00 (Gratis via Bober Pas)"
                val finalTotal = subtotal
                tvFinalTotal?.text = "€${String.format("%.2f", finalTotal)}"
            } else {
                tvDeliveryCost?.text = "€2,99"
                val finalTotal = subtotal + deliveryFee
                tvFinalTotal?.text = "€${String.format("%.2f", finalTotal)}"
            }
        }

        updatePrices()

        // Functie voor zijmenu
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

        // Functie voor uitschuifbaar paneel (Bober pas melding)
        fun togglePassSheet() {
            isSheetOpen = !isSheetOpen
            if (isSheetOpen) {
                layoutBottomSheetPass?.visibility = View.VISIBLE
                viewOverlay?.visibility = View.VISIBLE
                layoutBottomSheetPass?.animate()?.translationY(0f)?.duration = 300
                viewOverlay?.animate()?.alpha(1f)?.duration = 300
            } else {
                layoutBottomSheetPass?.animate()?.translationY(1000f)?.duration = 300
                viewOverlay?.animate()?.alpha(0f)?.duration = 300
                layoutBottomSheetPass?.postDelayed({
                    if (!isSheetOpen) {
                        layoutBottomSheetPass?.visibility = View.GONE
                        viewOverlay?.visibility = View.GONE
                    }
                }, 300)
            }
        }

        btnBackPayment?.setOnClickListener { toggleMenu() }
        viewOverlay?.setOnClickListener {
            if (isMenuOpen) toggleMenu()
            if (isSheetOpen) togglePassSheet()
        }
        btnCloseSheetPass?.setOnClickListener { togglePassSheet() }

        // Klik op "Bober Pas gebruiken" knop
        btnUseBoberPass?.setOnClickListener {
            val hasPass = sharedPreferences.getBoolean("HAS_BOBER_PASS", false)

            if (hasPass) {
                isBoberPassApplied = true
                tvSheetPassTitle?.text = "✨ Bober Pas Geactiveerd!"
                tvSheetPassDesc?.text = "Je Bober Pas is succesvol toegepast op deze bestelling. Je geniet van gratis bezorging (korting van €2,99)!"
                updatePrices()
            } else {
                isBoberPassApplied = false
                tvSheetPassTitle?.text = "❌ Geen Bober Pas gevonden"
                tvSheetPassDesc?.text = "Je hebt nog geen actieve Bober Pas. Activeer hem via het zijmenu bij 'Bober pas' om te genieten van gratis bezorging!"
            }
            togglePassSheet()
        }

        // Klik op "Bestelling definitief plaatsen"
        btnPlaceOrder?.setOnClickListener {
            CartManager.clearCart()
            Toast.makeText(this, "Bestelling succesvol geplaatst!", Toast.LENGTH_SHORT).show()

            // Stuur door naar Home of een succespagina (MainActivity)
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
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

        // Onderste navigatiebalk
        findViewById<LinearLayout>(R.id.btnNavHome10)?.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.btnNavSearch10)?.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }

        findViewById<LinearLayout>(R.id.btnNavCart10)?.setOnClickListener {
            startActivity(Intent(this, MainActivity3::class.java))
        }
    }
}