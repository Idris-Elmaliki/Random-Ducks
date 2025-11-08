package com.example.randomduck_app.data.repositories

import com.example.randomduck_app.data.DuckAPIDataClass
import com.example.randomduck_app.data.network.Dto.toDuckImage
import com.example.randomduck_app.data.network.Duck_API

class DuckRepositories(
    private val api : Duck_API
) {
    suspend fun getRandomPic() : DuckAPIDataClass {
        val duckDTO = api.getRandomPic()
        return duckDTO.toDuckImage()
    }
}