package com.example.randomduck_app.data.repositories

import com.example.randomduck_app.data.DuckFact
import com.example.randomduck_app.data.DuckImage
import com.example.randomduck_app.data.network.Dtos.toDuckFact
import com.example.randomduck_app.data.network.Dtos.toDuckImage
import com.example.randomduck_app.data.network.Duck_API

class DuckRepositories(
    private val api : Duck_API
) {
    suspend fun getRandomPic() : DuckImage {
        val picDto = api.getRandomPic()
        return picDto.toDuckImage()
    }

    suspend fun getRandomFact() : DuckFact {
        val factDto = api.getRandomfact()
        return factDto.toDuckFact()
    }
}