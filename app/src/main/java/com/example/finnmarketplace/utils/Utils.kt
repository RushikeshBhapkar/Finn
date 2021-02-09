package com.example.finnmarketplace.utils

import java.text.NumberFormat
import java.util.*

object Utils {
    fun getCurrencyFormattedValue(value: String): String {
        return if (value != null) {
            try {
                val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
                format.currency = Currency.getInstance("NOK")
                format.minimumFractionDigits = 0
                format.format(value.toDouble())
            } catch (ex: Exception) {
                "NOK0"
            }
        } else {
            "NOK0"
        }
    }
}
