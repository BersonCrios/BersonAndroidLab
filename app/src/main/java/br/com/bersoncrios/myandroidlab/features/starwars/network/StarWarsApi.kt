package br.com.bersoncrios.myandroidlab.features.starwars.network

import br.com.bersoncrios.myandroidlab.features.starwars.data.People
import br.com.bersoncrios.myandroidlab.features.starwars.data.Result
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface StarWarsApi {
    @GET("people")
    fun getPeople() : Deferred<List<People>>
}