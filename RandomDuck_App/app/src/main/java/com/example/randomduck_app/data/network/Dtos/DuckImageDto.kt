package com.example.randomduck_app.data.network.Dtos

import com.example.randomduck_app.data.DuckImage
import com.google.gson.annotations.SerializedName

data class DuckImageDto(
    @SerializedName("url")
    val url : String
)

fun DuckImageDto.toDuckImage() : DuckImage {
    return DuckImage(
        url = url
    )
}