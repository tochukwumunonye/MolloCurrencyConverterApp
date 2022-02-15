package com.tochukwu.mollocurrencyconverterapp.data

import com.tochukwu.mollocurrencyconverterapp.data.models.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {


    /**
     * This is the interface which fetches data from the endpoint of the Api
     */

    @GET("/latest")
   suspend fun getRates(
       @Query("base") base: String,
       @Query("access_key") access_key: String = "**INPUT YOUR PRIVATE API_KEY** SELECT MONTHLY PLAN"
   ) : Response<CurrencyResponse>


}


























































































//http://data.fixer.io/api/latest?access_key=5df5752931d4cb1fd15c560b5b356507&&symbols=CAD
