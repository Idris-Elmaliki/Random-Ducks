package com.example.randomduck_app.data.network

import com.example.randomduck_app.data.network.Dtos.DuckFactDto
import com.example.randomduck_app.data.network.Dtos.DuckImageDto
import retrofit2.http.GET

interface Duck_API {
    @GET("/random")
    suspend fun getRandomPic(): DuckImageDto

    @GET("/fact")
    suspend fun getRandomfact() : DuckFactDto
}