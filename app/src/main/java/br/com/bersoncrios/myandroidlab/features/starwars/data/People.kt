package br.com.bersoncrios.myandroidlab.features.starwars.data


import com.google.gson.annotations.SerializedName

data class People(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<Result>
)