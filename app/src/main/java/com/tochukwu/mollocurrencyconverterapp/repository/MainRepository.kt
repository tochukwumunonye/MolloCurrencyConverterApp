package com.tochukwu.mollocurrencyconverterapp.repository

import com.tochukwu.mollocurrencyconverterapp.data.models.CurrencyResponse
import com.tochukwu.mollocurrencyconverterapp.util.Resource


interface MainRepository {

    suspend fun getRates(base: String): Resource<CurrencyResponse>
}