package com.tochukwu.mollocurrencyconverterapp.repository

import com.tochukwu.mollocurrencyconverterapp.data.CurrencyApi
import com.tochukwu.mollocurrencyconverterapp.data.models.CurrencyResponse
import com.tochukwu.mollocurrencyconverterapp.util.Resource
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
     val api: CurrencyApi
) : MainRepository{

    override suspend fun getRates(base: String): Resource<CurrencyResponse> {

        return try{
            val response = api.getRates(base)
            val result = response.body()
            if(response.isSuccessful && result != null){
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch(e: Exception){
            Resource.Error(e.message ?: "An error occurred")
        }
    }
}

