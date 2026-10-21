package com.example.andriod_projectbobereats

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnStartShopping = findViewById<Button>(R.id.btnStartShopping)
        btnStartShopping?.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        // Verwijder bestelling knop met pop-up
        val btnDeleteOrder = findViewById<Button>(R.id.btnDeleteOrder)
        btnDeleteOrder?.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Bestelling verwijderen")
                .setMessage("Weet je zeker dat je alle producten uit je winkelmand wilt verwijderen?")
                .setPositiveButton("Ja") { _, _ ->
                    CartManager.clearCart()
                    updateCartUI()
                    Toast.makeText(this, "Winkelmand is geleegd", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Nee", null)
                .show()
        }

        val btnNavHome = findViewById<LinearLayout>(R.id.btnNavHome3)
        btnNavHome?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val btnNavSearch = findViewById<LinearLayout>(R.id.btnNavSearch3)
        btnNavSearch?.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        updateCartUI()
    }

    private fun updateCartUI() {
        val layoutEmptyCart = findViewById<LinearLayout>(R.id.layoutEmptyCart)
        val layoutFilledCart = findViewById<LinearLayout>(R.id.layoutFilledCart)
        val cartItemsContainer = findViewById<LinearLayout>(R.id.cartItemsContainer)
        val tvTotalPrice = findViewById<TextView>(R.id.tvTotalPrice)

        if (CartManager.items.isEmpty()) {
            layoutEmptyCart?.visibility = View.VISIBLE
            layoutFilledCart?.visibility = View.GONE
        } else {
            layoutEmptyCart?.visibility = View.GONE
            layoutFilledCart?.visibility = View.VISIBLE

            cartItemsContainer?.removeAllViews()

            for (item in CartManager.items) {
                val itemView = TextView(this).apply {
                    text = "${item.name} - €${String.format("%.2f", item.price)}"
                    textSize = 16f
                    setTextColor(android.graphics.Color.parseColor("#212529"))
                    setPadding(0, 16, 0, 16)
                }
                cartItemsContainer?.addView(itemView)
            }

            tvTotalPrice?.text = "Totaal: €${String.format("%.2f", CartManager.getTotalPrice())}"
        }
    }
}