package com.example.randomduck_app.data.network.Dto

import com.example.randomduck_app.data.DuckAPIDataClass
import com.google.gson.annotations.SerializedName

data class DuckImageDto(
    @SerializedName("url")
    val url : String,

    @SerializedName("message")
    val message : String,
)

fun DuckImageDto.toDuckImage() : DuckAPIDataClass {
    return DuckAPIDataClass(
        url = url,
        message = message
    )
}