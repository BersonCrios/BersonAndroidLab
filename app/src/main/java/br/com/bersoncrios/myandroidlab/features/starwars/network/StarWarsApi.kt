package br.com.bersoncrios.myandroidlab.features.starwars.network

import br.com.bersoncrios.myandroidlab.features.starwars.data.People
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface StarWarsApi {
    @GET("people")
    fun getPeople() : Deferred<People>

    @GET("people")
    fun getPeopleById(@Path("id") id:String) : Deferred<People>
}