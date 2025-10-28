package com.example.randomduck_app.data.network.Dtos

import com.example.randomduck_app.data.DuckFact
import com.google.gson.annotations.SerializedName

data class DuckFactDto(
    @SerializedName("fact")
    val fact: String
)

fun DuckFactDto.toDuckFact() : DuckFact {
    return DuckFact(
        fact = fact
    )
}