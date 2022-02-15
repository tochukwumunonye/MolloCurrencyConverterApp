package com.tochukwu.mollocurrencyconverterapp.data.models

//Model classes used to hold data the API returned
data class CurrencyResponse(
    val base: String,
    val date: String,
    val rates: Rates,
    val success: Boolean,
    val timestamp: Int
)