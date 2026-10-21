package com.example.andriod_projectbobereats

data class CartItem(
    val name: String,
    val price: Double
)

object CartManager {
    val items = mutableListOf<CartItem>()

    fun addItem(item: CartItem) {
        items.add(item)
    }

    fun getTotalPrice(): Double {
        return items.sumOf { it.price }
    }

    fun clearCart() {
        items.clear()
    }
}