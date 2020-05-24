package br.com.bersoncrios.myandroidlab.features.horizontalRv.model


import androidx.annotation.DrawableRes

data class Item(
    val title: String,
    @DrawableRes val icon: Int
)
