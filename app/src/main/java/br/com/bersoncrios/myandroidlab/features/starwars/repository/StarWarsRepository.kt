package br.com.bersoncrios.myandroidlab.features.starwars.repository

import br.com.bersoncrios.myandroidlab.core.service.ServiceResult
import br.com.bersoncrios.myandroidlab.features.starwars.data.People
import br.com.bersoncrios.myandroidlab.features.starwars.network.StarWarsApi

class StarWarsRepository constructor(private val starWarsApi : StarWarsApi){
    suspend fun getStarWars() : ServiceResult<People> {
        return try {
            val result = starWarsApi.getPeople().await()
            ServiceResult.Success(result)
        }
        catch (e : Exception){
            ServiceResult.Error(e)
        }
    }
}