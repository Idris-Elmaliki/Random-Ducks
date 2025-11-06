package com.example.randomduck_app.data

import com.example.randomduck_app.data.network.Duck_API
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://random-d.uk/api/v2/"

    val api : Duck_API by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Duck_API::class.java)
    }
}